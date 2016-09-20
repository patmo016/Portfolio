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
import java.util.TreeSet;

/**
 *
 * @author patmo016
 */
public class ProductDataStorage implements daoInterface{

    public ProductDataStorage() {
        
    }
    
    private static TreeMap<Integer,Product> productIDs = new TreeMap();
    
//    private static Collection<Product> products = new TreeSet();
    
//    private static Collection<String> catagories = new TreeSet();
    
    private static Multimap <String, Product> filterProducts = ArrayListMultimap.create();
            

    public Collection<String> getCatagories() {
        return filterProducts.keySet();
    }
    @Override
    public void saveProduct (Product product){
//        products.add(product);
//        catagories.add(product.getCategory());
        productIDs.put(product.getProductID(), product);
        filterProducts.put(product.getCategory(), product);
    }
    
    @Override
    public void deleteProduct (Product product){
//        products.remove(product);
//        catagories.remove(product.getCategory());
        //remove productID?
        filterProducts.remove(product.getCategory(),product);
        productIDs.remove(product.getProductID(), product);
        
    }
    @Override
    public Collection<Product> getProducts(){
        return productIDs.values();
    }
    @Override
    public Product searchID (int productID){
        Product value= productIDs.get(productID);
        if (value!= null){
            return value;
        }else{
            return null;
        }
    }
    @Override
    public Collection<Product> filterByCatagory(String cat){
       // System.out.println("found?");
        Collection<Product> catagories = filterProducts.get(cat);
        if (catagories != null){
           // System.out.println("not null");
            return catagories;
         
        }else{
            return null;
        }
    }
 

}
        
    
    
    

