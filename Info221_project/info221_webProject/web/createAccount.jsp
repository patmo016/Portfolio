<%-- 
    Document   : createAccount
    Created on : 5/09/2016, 5:06:25 PM
    Author     : patmo016
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create Account</h1>
        <%@include file="/WEB-INF/jspf/nav.jspf" %>

        <form action="createAccountServlet" method="POST">
            <label for="username">UserName:</label>
            <input id="txtUserName" name="customerUserName" type="text">
            <label for="txtName">Name:</label>
            <input id="txtName" name="customerName" type="text">
            <label for="txtEmail">Email:</label>
            <input id="txtEmail" name ="customerEmail" type="text">
            <label for="txtAddress">Address:</label>
            <input id="txtAddress" name="customerAddress" type="text">
            <label for="txtCreditCard">Credit Card Number:</label>
            <input id="txtCreditCard" name="customerCreditNum" type="text">
            <label for="txtPassword">Password:</label>
            <input id="txtPassword" name="customerPassword" type="text">          
            <button type="submit">Submit</button>
        </form>
    </body>
</html>
