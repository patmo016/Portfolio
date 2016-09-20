/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author patmo016
 */
public class OrderItem {
    private double quantityPurchased;
        private double purchasePrice;
    
    private Order newOrder;
    private Product purchasedProduct;

    public double getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(double quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Order getNewOrder() {
        return newOrder;
    }

    public void setNewOrder(Order newOrder) {
        this.newOrder = newOrder;
    }

    public Product getPurchasedProduct() {
        return purchasedProduct;
    }

    public void setPurchasedProduct(Product purchasedProduct) {
        this.purchasedProduct = purchasedProduct;
    }

    
    public double getItemTotal (){
        double totalresult = quantityPurchased * purchasePrice;
        return totalresult;
    }
 
    
}
