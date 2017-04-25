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

                int ehdokas_id = (Integer) request.getAttribute("ehdokas_id");

                List<Integer> ehdokkaanVastaukset = (List<Integer>) request.getAttribute("ehdokkaanVastaukset");
                List<Kysymykset> kaikkiKysymykset = (List<Kysymykset>) request.getAttribute("kaikkiKysymykset");

                for (int i = 1; i < ehdokkaanVastaukset.size(); i++) {

            %>
            <div id="kysymysvastaus">
                Kysymys <%= i%>: <%= kaikkiKysymykset.get(i - 1).getKysymys()%><br>
                Vastauksesi: <b><%= ehdokkaanVastaukset.get(i)%></b>
            </div>


            <% }%>
            <div class="kysymys"><small>1=Täysin eri mieltä 2=Osittain eri mieltä 3=En osaa sanoa, 4=Osittain samaa mieltä 5=Täysin samaa mieltä</small></div>

            <form method="POST">
                <input id="submitnappi" type="submit" name="laheta" value="Lähetä" />
            </form>
            <% //request.setAttribute("ehdokas_id", ehdokas_id);
                //request.setAttribute("ehdokkaanVastaukset", ehdokkaanVastaukset);
                //request.setAttribute("kaikkiKysymykset", kaikkiKysymykset);

                
                if (request.getParameter("laheta") != null) {
                   
                    request.getRequestDispatcher("/ETallennus")
                            .forward(request, response);  
                    
                }
            %>
        </div>
    </body>
</html>
