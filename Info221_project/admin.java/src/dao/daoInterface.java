/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import domain.Product;
import java.util.Collection;
import java.util.TreeMap;

/**
 *
 * @author patmo016
 */
public interface daoInterface {
    
    //method signitures
    
    public Collection<String> getCatagories();
     
    void saveProduct (Product product); 
    
    void deleteProduct (Product product);
    
    Collection<Product> getProducts();
    
    Product searchID (int productID);
    
    Collection<Product> filterByCatagory(String cat);
    


}
