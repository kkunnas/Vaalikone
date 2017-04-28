<%-- 
    Document   : Admin
    Created on : Apr 25, 2017, 9:27:56 AM
    Author     : toni1523
--%>

<%@page import="persist.Ehdokkaat"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.Query"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>

<% Object error = request.getAttribute("viesti");%>

<%
    if (session.getAttribute("admin") != "admin") {
        request.getRequestDispatcher("AKirjautuminen.jsp")
                .forward(request, response);
    }

    EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
    EntityManager em = emf.createEntityManager();
    Query qE = em.createQuery(
            "SELECT e FROM Ehdokkaat e");
    List<Ehdokkaat> ehdokasList = qE.getResultList();
%>

<!DOCTYPE html>
<html>
    <head>
        <link href="style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="container">

            <img id="headerimg" src="Logo.png" width="720" />
            <div class="kysymys">
                <h1>Ylläpito</h1>
            </div>

            <form id="vastausformi" action="HaeEhdokas">
                <b>Lista Ehdokkaista:</b></br>
                <select name="ehdokas_id">
                    <% for (int i = 1; i <= ehdokasList.size(); i++) {
                    %>

                    <option value="<%=ehdokasList.get(i - 1).getEhdokasId()%>"><%= ehdokasList.get(i - 1).getEhdokasId() + ". " + ehdokasList.get(i - 1).getEtunimi() + " " + ehdokasList.get(i - 1).getSukunimi()%></option>
                    <% }%>

                </select><br>
                <input id="seuraavanappi" type="submit" name="poistaEhdokas" value="Poista" />
                <input id="seuraavanappi" type="submit" name="haeEhdokas" value="Hae" />                   
            </form></br>

            <h3><a href="KMuokkaus.jsp">Kysymysten muokkaaminen</a></h3>
            <h3><a href="EMuokkaus.jsp">Ehdokkaiden lisääminen</a></h3>
            <small><a href="AKirjautuminen.jsp?logout=logout" >Kirjaudu ulos</a></small>

        </div>
    </body>
</html>
