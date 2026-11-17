package com.medicalstore.dao;

import com.medicalstore.model.Medicine;
import com.medicalstore.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAO {

    public boolean addMedicine(Medicine medicine) {

        String sql = "INSERT INTO medicines "
                + "(medicine_name, category, quantity, price, expiry_date) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, medicine.getMedicineName());
            ps.setString(2, medicine.getCategory());
            ps.setInt(3, medicine.getQuantity());
            ps.setDouble(4, medicine.getPrice());
            ps.setDate(5, medicine.getExpiryDate());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Medicine> getAllMedicines() {

        List<Medicine> medicines = new ArrayList<>();

        String sql = "SELECT * FROM medicines";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Medicine medicine = new Medicine();

                medicine.setId(rs.getInt("id"));
                medicine.setMedicineName(rs.getString("medicine_name"));
                medicine.setCategory(rs.getString("category"));
                medicine.setQuantity(rs.getInt("quantity"));
                medicine.setPrice(rs.getDouble("price"));
                medicine.setExpiryDate(rs.getDate("expiry_date"));

                medicines.add(medicine);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return medicines;
    }

    public Medicine getMedicineById(int id) {

        String sql = "SELECT * FROM medicines WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Medicine medicine = new Medicine();

                    medicine.setId(rs.getInt("id"));
                    medicine.setMedicineName(rs.getString("medicine_name"));
                    medicine.setCategory(rs.getString("category"));
                    medicine.setQuantity(rs.getInt("quantity"));
                    medicine.setPrice(rs.getDouble("price"));
                    medicine.setExpiryDate(rs.getDate("expiry_date"));

                    return medicine;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateMedicine(Medicine medicine) {

        String sql = "UPDATE medicines SET "
                + "medicine_name = ?, category = ?, quantity = ?, "
                + "price = ?, expiry_date = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, medicine.getMedicineName());
            ps.setString(2, medicine.getCategory());
            ps.setInt(3, medicine.getQuantity());
            ps.setDouble(4, medicine.getPrice());
            ps.setDate(5, medicine.getExpiryDate());
            ps.setInt(6, medicine.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // Delete medicine by ID
    public boolean deleteMedicine(int id) {

        String sql = "DELETE FROM medicines WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // Search Medicine by Name
    public List<Medicine> searchMedicine(String name) {

        List<Medicine> medicines = new ArrayList<>();

        String sql = "SELECT * FROM medicines WHERE LOWER(medicine_name) LIKE LOWER(?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + name.trim() + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Medicine medicine = new Medicine();

                    medicine.setId(rs.getInt("id"));
                    medicine.setMedicineName(rs.getString("medicine_name"));
                    medicine.setCategory(rs.getString("category"));
                    medicine.setQuantity(rs.getInt("quantity"));
                    medicine.setPrice(rs.getDouble("price"));
                    medicine.setExpiryDate(rs.getDate("expiry_date"));

                    medicines.add(medicine);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return medicines;
    }
}
