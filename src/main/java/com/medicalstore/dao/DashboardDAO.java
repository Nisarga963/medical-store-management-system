
package com.medicalstore.dao;

import com.medicalstore.model.Medicine;
import com.medicalstore.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DashboardDAO {

    // Get total medicines
    public int getTotalMedicines() {
        return getCount(
            "SELECT COUNT(*) FROM medicines"
        );
    }

    // Get total categories
    public int getTotalCategories() {
        return getCount(
            "SELECT COUNT(*) FROM categories"
        );
    }

    // Get total suppliers
    public int getTotalSuppliers() {
        return getCount(
            "SELECT COUNT(*) FROM suppliers"
        );
    }

    // Get low-stock medicines
    public int getLowStockMedicines() {
        return getCount(
            "SELECT COUNT(*) FROM medicines WHERE quantity <= 10"
        );
    }

    // Get expired medicines
    public int getExpiredMedicines() {
        return getCount(
            "SELECT COUNT(*) FROM medicines " +
            "WHERE expiry_date < CURDATE()"
        );
    }

    // Get medicines expiring within the next 30 days
    public int getNearExpiryMedicines() {
        return getCount(
            "SELECT COUNT(*) FROM medicines " +
            "WHERE expiry_date >= CURDATE() " +
            "AND expiry_date <= DATE_ADD(CURDATE(), INTERVAL 30 DAY)"
        );
    }

    // Common method to get counts
    private int getCount(String sql) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Error fetching dashboard count:");
            e.printStackTrace();
        }

        return 0;
    }

    // Get latest five medicines
    public List<Medicine> getRecentMedicines() {

        List<Medicine> medicines = new ArrayList<>();

        String sql =
            "SELECT id, medicine_name, category, quantity, price, expiry_date " +
            "FROM medicines ORDER BY id DESC LIMIT 5";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Medicine medicine = new Medicine();

                medicine.setId(rs.getInt("id"));
                medicine.setMedicineName(
                    rs.getString("medicine_name")
                );
                medicine.setCategory(
                    rs.getString("category")
                );
                medicine.setQuantity(
                    rs.getInt("quantity")
                );
                medicine.setPrice(
                    rs.getDouble("price")
                );
                medicine.setExpiryDate(
                    rs.getDate("expiry_date")
                );

                medicines.add(medicine);
            }

        } catch (Exception e) {
            System.out.println("Error fetching recent medicines:");
            e.printStackTrace();
        }

        return medicines;
    }
}
