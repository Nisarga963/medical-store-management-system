package com.medicalstore.dao;

import com.medicalstore.model.Supplier;
import com.medicalstore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {

    public boolean addSupplier(Supplier supplier) {
        String sql = "INSERT INTO suppliers (supplier_name, phone, email, address) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getPhone());
            ps.setString(3, supplier.getEmail());
            ps.setString(4, supplier.getAddress());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
public List<Supplier> getAllSuppliers() {

    List<Supplier> suppliers = new ArrayList<>();
    String sql = "SELECT * FROM suppliers";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Supplier supplier = new Supplier(
                rs.getInt("id"),
                rs.getString("supplier_name"),
                rs.getString("phone"),
                rs.getString("email"),
                rs.getString("address")
            );

            suppliers.add(supplier);
        }

        System.out.println("Total suppliers fetched: " + suppliers.size());

    } catch (Exception e) {
        System.out.println("Supplier database error:");
        e.printStackTrace();
    }

    return suppliers;
}

    public boolean updateSupplier(Supplier supplier) {
        String sql = "UPDATE suppliers SET supplier_name=?, phone=?, email=?, address=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getPhone());
            ps.setString(3, supplier.getEmail());
            ps.setString(4, supplier.getAddress());
            ps.setInt(5, supplier.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSupplier(int id) {
        String sql = "DELETE FROM suppliers WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}