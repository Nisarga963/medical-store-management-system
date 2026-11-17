
package com.medicalstore.controller;

import com.medicalstore.util.AccountFileStore;
import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null ||
            email.isBlank() || password.isBlank()) {
            response.sendRedirect("login.jsp?error=empty");
            return;
        }

        String[] account = AccountFileStore.findByEmail(email.trim());

        if (account != null &&
            BCrypt.checkpw(password, account[2])) {

            HttpSession session = request.getSession();
            session.setAttribute("userId", account[1]);
            session.setAttribute("userName", account[0]);
            session.setAttribute("userEmail", account[1]);
            session.setAttribute("userRole", account[3]);

            response.sendRedirect("dashboard");
        } else {
            response.sendRedirect("login.jsp?error=invalid");
        }
    }
}
