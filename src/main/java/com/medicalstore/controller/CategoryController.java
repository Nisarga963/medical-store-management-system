
package com.medicalstore.controller;

import com.medicalstore.dao.CategoryDAO;
import com.medicalstore.model.Category;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/categories")
public class CategoryController extends HttpServlet {

    private CategoryDAO categoryDAO;

    @Override
    public void init() {
        categoryDAO = new CategoryDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException {

        List<Category> categories = categoryDAO.getAllCategories();

        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/categories.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                String name = request.getParameter("categoryName");

                if (name != null && !name.trim().isEmpty()) {
                    categoryDAO.addCategory(name.trim());
                }

            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("categoryName");

                if (name != null && !name.trim().isEmpty()) {
                    categoryDAO.updateCategory(id, name.trim());
                }

            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                categoryDAO.deleteCategory(id);
            }

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                               "Invalid category ID");
            return;
        }

        response.sendRedirect(
            request.getContextPath() + "/categories"
        );
    }
}
