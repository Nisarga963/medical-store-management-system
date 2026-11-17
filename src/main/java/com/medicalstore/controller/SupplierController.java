package com.medicalstore.controller;

import com.medicalstore.dao.SupplierDAO;
import com.medicalstore.model.Supplier;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/suppliers")
public class SupplierController extends HttpServlet {

    private SupplierDAO supplierDAO;

    @Override
    public void init() {
        supplierDAO = new SupplierDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("suppliers", supplierDAO.getAllSuppliers());

        request.getRequestDispatcher("/suppliers.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {

            Supplier supplier = new Supplier(
                0,
                request.getParameter("supplierName"),
                request.getParameter("phone"),
                request.getParameter("email"),
                request.getParameter("address")
            );

            supplierDAO.addSupplier(supplier);

        } else if ("update".equals(action)) {

            Supplier supplier = new Supplier(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("supplierName"),
                request.getParameter("phone"),
                request.getParameter("email"),
                request.getParameter("address")
            );

            supplierDAO.updateSupplier(supplier);

        } else if ("delete".equals(action)) {

            int id = Integer.parseInt(request.getParameter("id"));
            supplierDAO.deleteSupplier(id);
        }

        response.sendRedirect(request.getContextPath() + "/suppliers");
    }
}