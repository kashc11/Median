package com.medianpckg.filters;

import com.medianpckg.helper.Keys;
import com.medianpckg.helper.Validator;
import com.medianpckg.service.UserService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = {"/register"})
public class RegisterFormFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    //used to process requests or responses
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        try {
            String firstName = request.getParameter("first-name").trim();
            String lastName = request.getParameter("last-name").trim();
            String emailOrMobile = request.getParameter("mobile-or-email").trim();
            String password = request.getParameter("user-password").trim();
            
            
            RequestDispatcher dis = request.getRequestDispatcher("register.jsp");
            
            
            if (!Validator.checkName(firstName)) {
                request.setAttribute(Keys.ERROR, "First name is invalid");
                dis.forward(request, response);
            } else if (!Validator.checkName(lastName)) {
                request.setAttribute(Keys.ERROR, "Last name is invalid");
                dis.forward(request, response);
            } else if (!Validator.checkEmailOrPhone(emailOrMobile)) {
                request.setAttribute(Keys.ERROR, "Email is invalid");
                dis.forward(request, response);
            } else if (!Validator.checkPassword(password)) {
                request.setAttribute(Keys.ERROR, "Password is invalid [mix: 4, max: 8, one digit, one upper case]");
                dis.forward(request, response);
            } else if (UserService.isDuplicateEmailOrPhone(emailOrMobile)) {
                request.setAttribute(Keys.ERROR, "Email/Phone is duplicated");
                dis.forward(request, response);
            } else {
                //And if all the inputs are correct, we keep forwarding to the RegisterController by calling
                chain.doFilter(request, response);
            }
        } catch (IOException | SQLException | ServletException ex) {
            Logger.getLogger(RegisterFormFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void destroy() {
    }

}
