
package com.medicalstore.controller;

import com.medicalstore.dao.MedicineDAO;
import com.medicalstore.model.Medicine;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/medicines")
public class MedicineController extends HttpServlet {

    private MedicineDAO medicineDAO;

    @Override
    public void init() {
        medicineDAO = new MedicineDAO();
    }

    // Check whether the user is logged in
    private boolean isLoggedIn(HttpServletRequest request,
                               HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(
                request.getContextPath() + "/login.jsp"
            );
            return false;
        }

        return true;
    }

    // Display, Search and Edit medicines
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        if (!isLoggedIn(request, response)) {
            return;
        }

        String action = request.getParameter("action");

        try {

            // SEARCH MEDICINE
            if ("search".equals(action)) {

                String searchName = request.getParameter("searchName");

                if (searchName == null) {
                    searchName = "";
                }

                request.setAttribute("searchName", searchName);

                request.setAttribute(
                    "medicines",
                    medicineDAO.searchMedicine(searchName)
                );

                request.getRequestDispatcher("/medicines.jsp")
                       .forward(request, response);

                return;
            }

            // EDIT MEDICINE
            if ("edit".equals(action)) {

                String idValue = request.getParameter("id");

                if (idValue == null || idValue.isBlank()) {
                    response.getWriter().println("Medicine ID is missing.");
                    return;
                }

                int id = Integer.parseInt(idValue);

                Medicine medicine = medicineDAO.getMedicineById(id);

                if (medicine != null) {
                    request.setAttribute("medicine", medicine);

                    request.getRequestDispatcher("/edit-medicine.jsp")
                           .forward(request, response);
                } else {
                    response.getWriter().println("Medicine not found.");
                }

                return;
            }

            // DISPLAY ALL MEDICINES
            request.setAttribute(
                "medicines",
                medicineDAO.getAllMedicines()
            );

            request.getRequestDispatcher("/medicines.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    // ADD, UPDATE AND DELETE MEDICINES
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        if (!isLoggedIn(request, response)) {
            return;
        }

        try {

            String action = request.getParameter("action");

            // DELETE MEDICINE
            if ("delete".equals(action)) {

                String idValue = request.getParameter("id");

                if (idValue == null || idValue.isBlank()) {
                    response.getWriter().println("Medicine ID is missing.");
                    return;
                }

                boolean result = medicineDAO.deleteMedicine(
                    Integer.parseInt(idValue)
                );

                if (result) {
                    response.sendRedirect(
                        request.getContextPath() + "/medicines"
                    );
                } else {
                    response.getWriter().println("Medicine deletion failed.");
                }

                return;
            }

            // READ FORM VALUES
            String name = request.getParameter("medicineName");
            String category = request.getParameter("category");
            String quantityValue = request.getParameter("quantity");
            String priceValue = request.getParameter("price");
            String expiryValue = request.getParameter("expiryDate");

            if (name == null || name.isBlank()
                    || category == null || category.isBlank()
                    || quantityValue == null || quantityValue.isBlank()
                    || priceValue == null || priceValue.isBlank()
                    || expiryValue == null || expiryValue.isBlank()) {

                response.getWriter().println("Please fill in all fields.");
                return;
            }

            Medicine medicine = new Medicine();

            medicine.setMedicineName(name);
            medicine.setCategory(category);
            medicine.setQuantity(Integer.parseInt(quantityValue));
            medicine.setPrice(Double.parseDouble(priceValue));
            medicine.setExpiryDate(Date.valueOf(expiryValue));

            boolean result;

            // UPDATE MEDICINE
            if ("update".equals(action)) {

                String idValue = request.getParameter("id");

                if (idValue == null || idValue.isBlank()) {
                    response.getWriter().println("Medicine ID is missing.");
                    return;
                }

                medicine.setId(Integer.parseInt(idValue));
                result = medicineDAO.updateMedicine(medicine);

            } else {
                // ADD MEDICINE
                result = medicineDAO.addMedicine(medicine);
            }

            if (result) {
                response.sendRedirect(
                    request.getContextPath() + "/medicines"
                );
            } else {
                response.getWriter().println("Operation failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}