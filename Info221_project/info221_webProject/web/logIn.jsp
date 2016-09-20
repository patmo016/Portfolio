<%-- 
    Document   : logIn
    Created on : 11/09/2016, 5:58:03 PM
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
        <h1>Log In</h1>
        <p> Please Log in to Continue.</p>
        <form action="loginServlet" method="POST">   
            <label for="username">UserName:</label>
            <input id="txtUserName" name="customerUserName" type="text">
            <input id="txtPassword" name="customerPassword" type="text">          
            <button type="submit">Log In</button>
       
        </form>
        <p>Don't have an account? Create one <a href="createAccount.jsp">Here</a>
            
    </body>
</html>
