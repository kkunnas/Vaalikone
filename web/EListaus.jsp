<%-- 
    Document   : Elistaus
    Created on : Apr 20, 2017, 1:29:56 PM
    Author     : Roope
--%>

<%@page import="org.hibernate.validator.constraints.Length"%>
<%@page import="java.util.List"%>
<%@page import="persist.Vastaukset"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vastauksien tarkistus</title>
        <link href="style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="container">
            <h1>Vastauksien tarkistus</h1>
            <% 
                List<Integer> ehdokkaanVastaukset = (List<Integer>) request.getAttribute("ehdokkaanVastaukset");
                
                
                for (int i = 1; i < ehdokkaanVastaukset.size(); i++){
                    
            %>
            <%= ehdokkaanVastaukset.get(i) %> </br>
            
            
            <% } %>
            Hello World!
        </div>
    </body>
</html>
