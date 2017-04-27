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
                    int vastaus = ehdokkaanVastaukset.get(i); 
            %>
            
           
            <div id="muokkaaminen">
                <div id="kysymysvastaus">
                Kysymys <%= i%>: <%= kaikkiKysymykset.get(i - 1).getKysymys()%><br>
            </div>
           
                
                <form method="POST"></br>
                <label>1</label><input type="radio" name="vastaus<%= i%>" value="1" <% if (vastaus ==1){ out.println("checked='checked'"); }%>/>
                <label>2</label><input type="radio" name="vastaus<%=  i%>" value="2" <% if (vastaus ==2){ out.println("checked='checked'");}%>/>
                <label>3</label><input type="radio" name="vastaus<%=  i%>" value="3" <% if (vastaus ==3){ out.println("checked='checked'");}%> />
                <label>4</label><input type="radio" name="vastaus<%=  i%>" value="4" <% if (vastaus ==4){ out.println("checked='checked'");}%>/>
                <label>5</label><input type="radio" name="vastaus<%=  i%>" value="5" <% if (vastaus ==5){ out.println("checked='checked'");}%>/>

           <%                                  
                }%>
            <input id="submitnappi" type="submit" name="laheta" value="Lähetä" />
            </form>
            
            </div>


            <div class="kysymys"><small>1=Täysin eri mieltä 2=Osittain eri mieltä 3=En osaa sanoa, 4=Osittain samaa mieltä 5=Täysin samaa mieltä</small></div>

                
            <%
                if (request.getParameter("laheta") != null) {
                    
                    request.getRequestDispatcher("/ETallennus")
                            .forward(request, response);
                }
            %>
            
        </div>
    </body>
</html>
