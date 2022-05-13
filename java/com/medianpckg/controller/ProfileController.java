package com.medianpckg.controller;

import com.medianpckg.helper.Keys;
import com.medianpckg.helper.Validator;
import com.medianpckg.model.User;
import com.medianpckg.service.UserService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/profile"})

public class ProfileController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        HttpSession sess = request.getSession();
        sess.setAttribute(Keys.ERROR, "");
        sess.setAttribute(Keys.SUCCESS, "");
        
        String action = request.getParameter("action");
        if (sess.getAttribute(Keys.USER) != null && 
                action !=null && action.equals("update-profile")) {
            try {
                String firstName = request.getParameter("first-name").trim();
                String lastName = request.getParameter("last-name").trim();
                String newEmailOrMobile = request.getParameter("mobile-or-email").trim();
                String userPass = request.getParameter("user-password").trim();
                String day = request.getParameter("day").trim();
                String month = request.getParameter("month").trim();
                String year = request.getParameter("year").trim();
                String sex = request.getParameter("sex").trim();
                String birthday = String.format("%s-%s-%s", day, month, year);
                
                String currentEmailOrMobile = ((User)sess.getAttribute("user")).getEmailOrMobile();
                
                if (UserService.updateUser(firstName, lastName, newEmailOrMobile,
                            userPass, birthday, sex, currentEmailOrMobile) == Validator.SUCCESS) {
                    // update session
                    User user = UserService.getUser(newEmailOrMobile);
                    sess.setAttribute(Keys.USER, user); 
                    sess.setAttribute(Keys.SUCCESS, "Data has been saved!");
                    
                } else {
                    
                    sess.setAttribute(Keys.ERROR, "Something wrong! Please, try again!");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (sess.getAttribute(Keys.USER) != null) {
            RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/profile.jsp");
            dis.forward(request, response);
        } else { // not logged in
            response.sendRedirect("login.jsp");
        }
}
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}