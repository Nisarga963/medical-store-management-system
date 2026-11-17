
package com.medicalstore.controller;

import com.medicalstore.dao.MedicineDAO;
import com.medicalstore.model.Medicine;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/medicines")
public class MedicineController extends HttpServlet {

    private MedicineDAO medicineDAO;

    @Override
    public void init() {
        medicineDAO = new MedicineDAO();
    }

    // DISPLAY, SEARCH AND EDIT MEDICINES
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {

            // SEARCH MEDICINE
            if ("search".equals(action)) {

                String searchName = request.getParameter("searchName");

                if (searchName == null) {
                    searchName = "";
                }

                List<Medicine> searchResults =
                        medicineDAO.searchMedicine(searchName);

                System.out.println("SEARCH ACTION WORKING");
                System.out.println("Search name: " + searchName);
                System.out.println("Search results count: "
                        + searchResults.size());

                request.setAttribute("medicines", searchResults);
                request.setAttribute("searchName", searchName);

                request.getRequestDispatcher("/medicines.jsp")
                        .forward(request, response);

            }

            // EDIT MEDICINE
            else if ("edit".equals(action)) {

                String idValue = request.getParameter("id");

                if (idValue == null || idValue.isBlank()) {
                    response.getWriter().println(
                            "Error: Medicine ID is missing.");
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

            }

            // DISPLAY ALL MEDICINES
            else {

                List<Medicine> allMedicines =
                        medicineDAO.getAllMedicines();

                request.setAttribute("medicines", allMedicines);

                request.getRequestDispatcher("/medicines.jsp")
                        .forward(request, response);
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Error: " + e.getMessage());
        }
    }

    // ADD, UPDATE AND DELETE MEDICINES
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String action = request.getParameter("action");

            // DELETE MEDICINE
            if ("delete".equals(action)) {

                String idValue = request.getParameter("id");

                if (idValue == null || idValue.isBlank()) {
                    response.getWriter().println(
                            "Error: Medicine ID is missing.");
                    return;
                }

                int id = Integer.parseInt(idValue);

                boolean result = medicineDAO.deleteMedicine(id);

                if (result) {
                    response.sendRedirect(
                            request.getContextPath() + "/medicines");
                } else {
                    response.getWriter().println(
                            "Error: Medicine deletion failed.");
                }

                return;
            }

            // GET FORM VALUES
            String name = request.getParameter("medicineName");
            String category = request.getParameter("category");
            String quantityValue = request.getParameter("quantity");
            String priceValue = request.getParameter("price");
            String expiryValue = request.getParameter("expiryDate");

            // VALIDATE FORM
            if (name == null || name.isBlank()
                    || category == null || category.isBlank()
                    || quantityValue == null || quantityValue.isBlank()
                    || priceValue == null || priceValue.isBlank()
                    || expiryValue == null || expiryValue.isBlank()) {

                response.getWriter().println(
                        "Error: Please fill in all fields.");
                return;
            }

            int quantity = Integer.parseInt(quantityValue);
            double price = Double.parseDouble(priceValue);
            Date expiryDate = Date.valueOf(expiryValue);

            Medicine medicine = new Medicine();

            medicine.setMedicineName(name);
            medicine.setCategory(category);
            medicine.setQuantity(quantity);
            medicine.setPrice(price);
            medicine.setExpiryDate(expiryDate);

            boolean result;

            // UPDATE MEDICINE
            if ("update".equals(action)) {

                String idValue = request.getParameter("id");

                if (idValue == null || idValue.isBlank()) {
                    response.getWriter().println(
                            "Error: Medicine ID is missing.");
                    return;
                }

                int id = Integer.parseInt(idValue);

                medicine.setId(id);

                result = medicineDAO.updateMedicine(medicine);

            }

            // ADD MEDICINE
            else {

                result = medicineDAO.addMedicine(medicine);
            }

            if (result) {

                response.sendRedirect(
                        request.getContextPath() + "/medicines");

            } else {

                response.getWriter().println(
                        "Error: Operation failed.");
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                    "Error: " + e.getMessage());
        }
    }
}
