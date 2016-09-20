/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.DAOException;
import dao.JdbcConnection;
import domain.Customer;
import domain.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author patmo016
 */
public class Customerdata {
     private String url = "jdbc:h2:tcp://localhost:9060/project;IFEXISTS=TRUE";

    //constructer to initialise the URL field
    public Customerdata (String newUrl) {
        this.url = newUrl;
    }

        public Customerdata() {
    }  

     public void saveCustomer (Customer customer){   
         String customerInfo = customer.toString();
         System.out.println (customerInfo);
 
 
        String sql ="merge into customers (username, name, email, address, creditcard, password,) "
                + "values (?, ?, ?, ?, ?, ?)";
        try( Connection dbCon = JdbcConnection.getConnection(url);
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
           stmt.setString(1, customer.getUserName());
           stmt.setString(2, customer.getName());
           stmt.setString(3, customer.getEmail());
           stmt.setString(4, customer.getAddress());
           stmt.setString(5, customer.getCreditCard());
           stmt.setString(6, customer.getPassword());
           stmt.executeUpdate();
       } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }
    
    public Customer getCustomer (String username, String password){
        String sql = "select * from customers where username = ? AND password = ?;";
        try (
                // get connection to the database
                Connection dbCon = JdbcConnection.getConnection(url);
                //create statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String creditcard = rs.getString("creditcard");
                String email = rs.getString("email");
                String address = rs.getString("address");
                
                Customer newCustomer = new Customer(username, name, email, address, creditcard, password);
                return newCustomer;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new DAOException (ex.getMessage(), ex);
        }

    }
    
}
