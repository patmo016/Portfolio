/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
public class DbStorage implements daoInterface {
    
     private String url = "jdbc:h2:tcp://localhost:9060/project;IFEXISTS=TRUE";

    //constructer to initialise the URL field
    public DbStorage(String newUrl) {
        this.url = newUrl;
    }

        public DbStorage() {
    }
    
    
     @Override
    public Collection<String> getCatagories() {
        String sql = "select distinct category from products "
                + "order by category;";//add sql
        try (
                // get connection to the database
                Connection dbCon = JdbcConnection.getConnection(url);
                //create statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            //add further get statements

            ResultSet rc = stmt.executeQuery();

            List<String> categories = new ArrayList();
            while (rc.next()) {
                String c = rc.getString("category");
                categories.add(c);

            }

            return categories;

        } catch (SQLException ex) {
            throw new DAOException (ex.getMessage(), ex);
        }

        //ADD RETURN STATEMENT?
    }

    @Override
    public void saveProduct(Product product) {
        String sql = "merge into products (productID, name, description, category, price, quantityStock) "
                + "values (?, ?, ?, ?, ?, ?)";
        try (
                // get connection to the database
                Connection dbCon = JdbcConnection.getConnection(url);
                //create statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            stmt.setInt(1, product.getProductID());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setString(4, product.getCategory());
            stmt.setDouble(5, product.getPrice());
            stmt.setDouble(6, product.getQuantityStock());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteProduct(Product product) {
        String sql = "delete from products where productID = ?";
        try (
                // get connection to the database
                Connection dbCon = JdbcConnection.getConnection(url);
                //create statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {

             stmt.setInt(1, product.getProductID());       
            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Collection<Product> getProducts() {
        String sql = "select * from products order by productid";
        try (
                // get a connection to the database
                Connection dbCon = JdbcConnection.getConnection(url);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // execute the query
            ResultSet rs = stmt.executeQuery();
            // Create a collection for holding the student we get from the query.
            // We are using a List in order to preserve the order in which
            // the data was returned from the query.
            List<Product> products = new ArrayList<>();
            // iterate through the query results
            while (rs.next()) {
                // get the data out of the query
                Integer productID = rs.getInt("productID");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                Double price = rs.getDouble("price");
                Double quantityStock = rs.getDouble("quantityStock");
                // use the data to create a product object
                Product newProduct = new Product(productID, name, description, category, price, quantityStock);
                // and put it in the collection
                products.add(newProduct);
            }
            return products;
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

    }

    @Override
    public Product searchID(int productID) {
        String sql = "select * from products where productid =?";
        try (
                // get connection to the database
                Connection dbCon = JdbcConnection.getConnection(url);
                //create statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {

            stmt.setInt(1, productID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Integer id = rs.getInt("productID");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                Double price = rs.getDouble("price");
                Double quantityStock = rs.getDouble("quantityStock");
                // use the data to create a product object
                Product newProduct = new Product(id, name, description, category, price, quantityStock);
                return newProduct;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new DAOException (ex.getMessage(), ex);
        }

    }

    @Override
    public Collection<Product> filterByCatagory(String cat) {
        String sql = "select * from products where category = ?";
        try (
            // get connection to the database
            Connection dbCon = JdbcConnection.getConnection(url);
            //create statement
            PreparedStatement stmt = dbCon.prepareStatement(sql);
            ){
             List<Product> products = new ArrayList<>();
                stmt.setString(1, cat);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Integer id = rs.getInt("productID");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String category = rs.getString("category");
                    Double price = rs.getDouble("price");
                    Double quantityStock = rs.getDouble("quantityStock");
                    // use the data to create a product object
                    Product newProduct = new Product(id, name, description, category, price, quantityStock);
                    products.add(newProduct);
                } 
                return products;
            }catch (SQLException ex){
                     throw new DAOException(ex.getMessage(), ex);
               }
        }
}
