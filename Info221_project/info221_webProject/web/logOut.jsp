<%-- 
    Document   : logOut
    Created on : 13/09/2016, 9:35:25 PM
    Author     : patmo016
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
        <h1>YOU ARE LOGGED OUT</h1>
        
        <% session.invalidate();%>
        <%response.sendRedirect("home.jsp");%>
        

    

