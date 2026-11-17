
package com.medicalstore.controller;

import com.medicalstore.dao.DashboardDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Check whether the user is logged in
        HttpSession session = request.getSession(false);

        if (session == null ||
            session.getAttribute("userId") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        // Get dashboard statistics
        DashboardDAO dao = new DashboardDAO();

        request.setAttribute("totalMedicines",
                             dao.getTotalMedicines());

        request.setAttribute("totalCategories",
                             dao.getTotalCategories());

        request.setAttribute("totalSuppliers",
                             dao.getTotalSuppliers());

        request.setAttribute("lowStockMedicines",
                             dao.getLowStockMedicines());

        request.setAttribute("expiredMedicines",
                             dao.getExpiredMedicines());

        request.setAttribute("nearExpiryMedicines",
                             dao.getNearExpiryMedicines());

        request.setAttribute("recentMedicines",
                             dao.getRecentMedicines());

        // Open dashboard page
        request.getRequestDispatcher("/dashboard.jsp")
               .forward(request, response);
    }
}
