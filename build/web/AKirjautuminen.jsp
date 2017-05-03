<%-- 
    Document   : AKirjautuminen
    Created on : Apr 25, 2017, 9:33:23 AM
    Author     : toni1523
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>

<% Object error = request.getAttribute("viesti");  %>

<!DOCTYPE html>
<html>
    <head>
        <link href="style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <% //Tarkistetaan onko sessio voimassa
            if (request.getParameter("logout") != null) {
                        session.setAttribute("admin", null);
            }
        %>
        <div id="container">

            <img id="headerimg" src="Logo.png" width="720" />
            <div class="kysymys">
                <h1>Vaalikone</h1>
            </div>

            <form id="vastausformi" action="STarkistus" method="POST">  
                <% if (error != null) {out.println(error);%> </br> <%} else {%> Syötä käyttäjätunnus ja salasana</br> <%}%>
                <input id="kayttajatunnus" type="text" name="kayttajatunnus" placeholder="Käyttäjätunnus">
                <input id="salasana" type="password" name="salasana" placeholder="Salasana" >
                <input id="submitnappi" type="submit" name="kirjaudu" value="Kirjaudu">                   
            </form></br>

        </div>
    </body>
</html>
