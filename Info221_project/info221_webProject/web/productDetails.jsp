<%-- 
    Document   : productDetails
    Created on : 11/09/2016, 3:49:18 PM
    Author     : patmo016
--%>

<%@page import="dao.DbStorage"%>
<%@page import="dao.daoInterface"%>
<%@page import="java.util.Collection"%>
<%@page import="domain.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<% daoInterface dao = new DbStorage(); %>
<% Collection<Product> products = dao.getProducts(); %>
<% Collection<String> categories = dao.getCatagories();%>


        

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product details Page</title>
    </head>
    <body>   
        <h1>Products</h1>
        <%@include file="/WEB-INF/jspf/nav.jspf" %>

        <table>
            <thead>
                <tr>
                    <th>PRODUCTS</th>
                    </th>
            </thead>
            
            <tbody>
                <tr>
                    <td>Categories: </td>
                    <td><button type="button" onclick="<% products= dao.getProducts();%>">All</button></td>
                    <%for (String cat: categories){ %>
                    <td><button type="button" > <%=cat%></button> </td>
                </tr>
                    
                    
            
                <%}%>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Quantity in Stock</th>
                    
                </tr>
                     <% for (Product product: products){ %>
                <tr>
                    <td><%= product.getName() %></td>
                    <td><%= product.getDescription() %></td>
                    <td><%= product.getPrice() %></td>
                    <td><%= product.getQuantityStock() %></td>
                    <td><button type='button'>Buy</button></td>
                </tr>
                <% } %>
            </tbody
            </thread>
    </table>
    </body>
</html>
