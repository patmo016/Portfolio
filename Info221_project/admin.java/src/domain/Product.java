/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.logging.Logger;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNegative;
import net.sf.oval.constraint.NotNull;

/**
 *
 * @author patmo016
 */
public class Product implements Comparable<Product> {

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.productID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.productID != other.productID) {
            return false;
        }
        return true;
    }
    
    public Product(Integer productID, String name, String description, String category, Double price, Double quantityStock) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.quantityStock = quantityStock;
    }

    

   
    @NotNull(message = "ID must be provided.")
    @Length(min=2, message="ID must contain at least two characters.")
    private Integer productID;
    
    @NotNull(message = "Name must be provided.")
    @NotBlank(message="Name must be provided.")
    @Length(min=2, message="Name must contain at least two characters.")
    private String name;
    
    @NotNull(message = "Description must be provided.")
    @NotBlank(message="Description must be provided.")
    @Length(min=2, message="Description must contain at least two characters.")
    private String description;
    
    @NotNull(message = "Category must be added or selected")
    @NotBlank(message="Category must be added or selected")
    private String category;
    
    @NotNull(message = "Price must be provided.")
    @NotNegative(message = "Price must be a positive number.")
    private Double price;
    
    @NotNull(message = "Quantity of Stock must be provided.")
    @NotNegative(message = "Quantity must be a positive number.")
    private Double quantityStock;

    public Product() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public double getQuantityStock() {
        return quantityStock;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantityStock(double quantityStock) {
        this.quantityStock = quantityStock;
    }

    @Override
    public String toString() {
        return "Productdomain{" + "productID=" + productID + ", name=" + name 
                + ",Category= "+ category +", Description="+ description +
               ", Price= " + price + ", Quantity= " + quantityStock +'}';
             
    }

    @Override
    public int compareTo(Product nextProduct) {
        Integer id = this.getProductID();
        Integer newID =nextProduct.getProductID();
        return id.compareTo(newID);
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
 
    
            
    
}
