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
public class Customer {

    public Customer(String userName, String name, String email, String address, String creditCard, String password) {
        this.userName = userName;
        this.name = name;
        this.creditCard = creditCard;
        this.password = password;
        this.email = email;
        this.address =address;
                
    }
    
    private String userName;
    private String name;
    private String creditCard;
    private String password;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    private String address;
    

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "Customer{" + " username=" + userName + ", name=" + name 
                +",Email = "+email+" Address=  "+ address+ ",Creditcard= "+ 
                        creditCard +", Password= "+ password +'}';
              
    }

}
