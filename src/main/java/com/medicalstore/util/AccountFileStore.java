
package com.medicalstore.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;

public class AccountFileStore {

    private static final Path FILE = Path.of(
        System.getProperty("user.home"),
        "medical_store_accounts.txt"
    );

    private static String encode(String value) {
        return Base64.getEncoder().encodeToString(
            value.getBytes(StandardCharsets.UTF_8)
        );
    }

    private static String decode(String value) {
        return new String(
            Base64.getDecoder().decode(value),
            StandardCharsets.UTF_8
        );
    }

    public static synchronized boolean register(
            String fullName, String email, String passwordHash)
            throws IOException {

        Files.createDirectories(FILE.getParent());

        if (Files.exists(FILE)) {
            for (String line : Files.readAllLines(FILE)) {
                String[] parts = line.split("\\t", -1);

                if (parts.length == 4 &&
                    decode(parts[1]).equalsIgnoreCase(email)) {
                    return false;
                }
            }
        }

        String record = encode(fullName) + "\t"
                + encode(email) + "\t"
                + encode(passwordHash) + "\t"
                + encode("user");

        Files.writeString(
            FILE,
            record + System.lineSeparator(),
            StandardCharsets.UTF_8,
            java.nio.file.StandardOpenOption.CREATE,
            java.nio.file.StandardOpenOption.APPEND
        );

        return true;
    }

    public static synchronized String[] findByEmail(String email)
            throws IOException {

        if (!Files.exists(FILE)) {
            return null;
        }

        List<String> lines = Files.readAllLines(FILE);

        for (String line : lines) {
            String[] parts = line.split("\\t", -1);

            if (parts.length == 4 &&
                decode(parts[1]).equalsIgnoreCase(email)) {

                return new String[] {
                    decode(parts[0]),
                    decode(parts[1]),
                    decode(parts[2]),
                    decode(parts[3])
                };
            }
        }

        return null;
    }
}
