package com.medianpckg.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.medianpckg.helper.Keys;
import com.medianpckg.model.User;
import com.medianpckg.service.PostService;

@WebServlet(urlPatterns = {"/posts"})
public class PostController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> options = new HashMap<>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Keys.USER);

        String action = request.getParameter("action");

        try {
            if (action != null && action.equals("add-post")) {
                String postContent = request.getParameter("post-content");
                PostService.addNewPost(user.getId(), postContent);
                
            } else if (action != null && action.equals("add-comment")) {
                int postId = Integer.parseInt(request.getParameter("post-id"));
                String commentContent = request.getParameter("comment-content");
                PostService.addNewComment(user.getId(), postId, commentContent);
            }
            options.put("result", "OK");
        } catch (SQLException ex) {
            options.put("result", "FAILED");
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String json = new Gson().toJson(options);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
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


