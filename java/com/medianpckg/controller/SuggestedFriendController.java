package com.medianpckg.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.medianpckg.helper.Keys;
import com.medianpckg.model.User;
import com.medianpckg.service.UserService;

@WebServlet(urlPatterns = {"/get-suggested-friend-list"})
public class SuggestedFriendController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(Keys.USER);

            String action = request.getParameter("action");
            if (action != null && action.equals("add-friend")) {
                int friendId = Integer.parseInt(request.getParameter("friendId"));
                UserService.addFriend(user.getId(), friendId);
            }

            List<User> list = UserService.getSuggestedFriend(user.getId());
            session.setAttribute(Keys.SUGGESTED_FRIEND_LIST, list);
            RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/suggested-friend.jsp");
            dis.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SuggestedFriendController.class.getName()).log(Level.SEVERE, null, ex);
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
