package com.medianpckg.listeners;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.medianpckg.service.DBService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener() 
//this java class will act as a web listener
public class AppListener implements ServletContextListener {
    //when app is initialized
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext app = sce.getServletContext();
        
        // to get the corresponding value
        DBService.DRIVER = app.getInitParameter("db.driver");
        DBService.URL = app.getInitParameter("db.url");
        DBService.USER = app.getInitParameter("db.user");
        DBService.PASSWORD = app.getInitParameter("db.password");
        
        
        Connection connection = DBService.openConnection();
        
        //testing the connection
        if (connection != null) {
            try {
                System.out.println("Connected to : " + connection.getCatalog());
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AppListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Cannot connect to database");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("The web app has been shut down");
    }
}
