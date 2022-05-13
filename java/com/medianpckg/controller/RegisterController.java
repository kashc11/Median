package com.medianpckg.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.medianpckg.helper.Validator;
import com.medianpckg.helper.Keys;
import com.medianpckg.service.UserService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

// used to inform the web browser that this is the way to send requests to the RegisterController servlet
@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    //invoked in both doGet() and doPost() methods
      protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String firstName = request.getParameter("first-name").trim();
            String lastName = request.getParameter("last-name").trim();
            String emailOrMobile = request.getParameter("mobile-or-email").trim();
            String password = request.getParameter("user-password").trim();
            String day = request.getParameter("day").trim();
            String month = request.getParameter("month").trim();
            String year = request.getParameter("year").trim();
            String sex = request.getParameter("sex").trim();
            
            //used to forward
            RequestDispatcher dis = request.getRequestDispatcher("register.jsp");
            
            
            String birthday = String.format("%s-%s-%s", day, month, year);
            
            
            // Add to database
            if (UserService.addNewUser(firstName, lastName, emailOrMobile,
                    password, birthday, sex) == Validator.SUCCESS) {
                response.sendRedirect("login.jsp");
            } else {
                request.setAttribute(Keys.ERROR, "Something wrong! Please, try again!");
                dis.forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
