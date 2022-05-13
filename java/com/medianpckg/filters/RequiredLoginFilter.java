package com.medianpckg.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.medianpckg.helper.Keys;


// filter will intercept requests with these 2 URL patterns
@WebFilter(urlPatterns = {"/profile", "/newsfeed"})


public class RequiredLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {              
    }   
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        try {
            HttpSession session = httpServletRequest.getSession();
            if (session.getAttribute(Keys.USER) == null) {
                String currentUrl = httpServletRequest.getRequestURL().toString();
                session.setAttribute(Keys.CURRENT_URL, currentUrl);
                httpServletResponse.sendRedirect("login.jsp");
            } else {
                chain.doFilter(request, response);
            }           
        } catch (IOException | ServletException ex) {
        }
        
    }
    @Override
    public void destroy() {        
    }
}