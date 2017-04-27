<%-- 
    Document   : Admin
    Created on : Apr 25, 2017, 9:27:56 AM
    Author     : toni1523
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%
            if (session.getAttribute("admin") != "admin") {
                request.getRequestDispatcher("AKirjautuminen.jsp")
                        .forward(request, response);
            }
        %>
        <div id="container">

            <img id="headerimg" src="Logo.png" width="720" />
            <div class="kysymys">
                <h1>Ylläpito</h1>
            </div>

            <form id="vastausformi" action="HaeEhdokas">
                Syötä ehdokkaan id</br>
                <input id="ehdokas_id" type="text" name="ehdokas_id" /></br>
                <input id="seuraavanappi" type="submit" name="poistaEhdokas" value="Poista" />
                <input id="seuraavanappi" type="submit" name="haeEhdokas" value="Hae" />                   
            </form></br>

            <h3><a href="KMuokkaus.jsp">Kysymysten muokkaaminen</a></h3>
            <h3><a href="EMuokkaus.jsp">Ehdokkaiden lisääminen</a></h3>
            <small><a href="AKirjautuminen.jsp?logout=logout" >Kirjaudu ulos</a></small>

        </div>
    </body>
</html>
