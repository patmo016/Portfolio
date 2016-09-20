/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author patmo016
 */
public class Order {

    public Order() {
    }
    private Integer orderId;
    private Integer date;
    private Customer orderCustomer;
    private List<OrderItem> items = new ArrayList<OrderItem>();    
   // public Collection addItem (OrderItem newOrderItem){
        //add order item to the items collection
    //} 

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Customer getOrderCustomer() {
        return orderCustomer;
    }

    public void setOrderCustomer(Customer orderCustomer) {
        this.orderCustomer = orderCustomer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    
    public double getTotal (){
        return 0;
        //return orderItem.getItemTotal();
    }
         
 
    
    
}
