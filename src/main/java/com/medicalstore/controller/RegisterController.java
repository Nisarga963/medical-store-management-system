
package com.medicalstore.controller;

import com.medicalstore.util.AccountFileStore;
import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword =
                request.getParameter("confirmPassword");

        if (fullName == null || email == null ||
            password == null || confirmPassword == null ||
            fullName.isBlank() || email.isBlank() ||
            password.isBlank() || confirmPassword.isBlank()) {

            response.sendRedirect("register.jsp?error=empty");
            return;
        }

        if (password.length() < 8) {
            response.sendRedirect("register.jsp?error=short");
            return;
        }

        if (!password.equals(confirmPassword)) {
            response.sendRedirect("register.jsp?error=mismatch");
            return;
        }

        try {
            String hashedPassword = BCrypt.hashpw(
                password, BCrypt.gensalt()
            );

            boolean created = AccountFileStore.register(
                fullName.trim(),
                email.trim(),
                hashedPassword
            );

            if (created) {
                response.sendRedirect("login.jsp?registered=success");
            } else {
                response.sendRedirect("register.jsp?error=exists");
            }

        } catch (IOException e) {
            throw new ServletException(
                "Unable to save account details.", e
            );
        }
    }
}
