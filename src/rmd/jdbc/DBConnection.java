/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmd.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fariz
 */
public class DBConnection {
        
              static String URL = "jdbc:mysql://localhost:3306/realmadrid?allowMultiQueries=true&zeroDateTimeBehavior=convertToNull";
              static String USERNAME = "root";
              static String PASSWORD = "mysqlserver";
              static Connection connection=null;
 
    public static Connection getDBConnection(){
         
        try {
                      Class.forName("com.mysql.jdbc.Driver").newInstance();
                      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                  } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
                      Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                  }
         
    return connection;
    }

}
