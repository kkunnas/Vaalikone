<%-- 
    Document   : Elistaus
    Created on : Apr 20, 2017, 1:29:56 PM
    Author     : Roope
--%>

<%@page import="persist.Kysymykset"%>
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
                List<Kysymykset> kaikkiKysymykset = (List<Kysymykset>) request.getAttribute("kaikkiKysymykset");

                for (int i = 1; i < ehdokkaanVastaukset.size(); i++) {
            %>
            <div id="kysymysvastaus">
                Kysymys <%= i%>: <%= kaikkiKysymykset.get(i - 1).getKysymys()%><br>
                Vastauksesi: <b><%= ehdokkaanVastaukset.get(i)%></b>
            </div>
            <% }%>
            <div id="muokkaaminen">
                <form method="POST">
                    <b>Muokkaaminen:</b></br>
                    Kysymys: 
                    <select name="kysymys">
                        <% for (int i = 1; i <= kaikkiKysymykset.size(); i++) {%>
                        <option value="<%= i%>"><%= i%></option>
                        <% }%>
                    </select>
                    Vastaus: 
                    <select name="vastaus">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                    <button name="tallenna" value="Tallenna">Tallenna</button>
                </form>
            </div>

            <%
                if (request.getParameter("tallenna") != null) {
                    String kysymys = request.getParameter("kysymys");
                    String vastaus = request.getParameter("vastaus");

                    int k = Integer.parseInt(kysymys);
                    int v = Integer.parseInt(vastaus);

                    ehdokkaanVastaukset.set(k, v);

                    response.setHeader("Refresh", "0; http://localhost:8080/vaalikone/Vaalikone?EVastaus=" + ehdokkaanVastaukset.get(19) + "&q=19");

                }

            %>

            <div class="kysymys"><small>1=Täysin eri mieltä 2=Osittain eri mieltä 3=En osaa sanoa, 4=Osittain samaa mieltä 5=Täysin samaa mieltä</small></div>
            <form method="POST">
                <input id="submitnappi" type="submit" name="laheta" value="Lähetä" />
            </form>
            <%
                if (request.getParameter("laheta") != null) {

                    request.getRequestDispatcher("/ETallennus")
                            .forward(request, response);
                }
            %>
        </div>
    </body>
</html>
