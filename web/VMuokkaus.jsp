<%-- 
    Document   : VMuokkaus
    Created on : Apr 27, 2017, 9:08:10 AM
    Author     : karoliina1506
--%>

<%@page import="javax.swing.text.Document"%>
<%@page import="persist.Kysymykset"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Diginide Vaalikone 2.0</title>

        <link href="style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="container">
        <img id="headerimg" src="images/Logo.png" width="500" height="144" alt=""/>
        
        
        <!-- TÄMÄ EI OLE KÄYTÖSSÄ -->
        
            <h1>Vastauksien tarkistus</h1>
            <%
                List<Integer> ehdokkaanVastaukset = (List<Integer>) request.getAttribute("ehdokkaanVastaukset");
                List<Kysymykset> kaikkiKysymykset = (List<Kysymykset>) request.getAttribute("kaikkiKysymykset");

                for (int i = 0; i < ehdokkaanVastaukset.size(); i++) {

                    int vastaus = ehdokkaanVastaukset.get(i);
            %>
            <div class="kysymys">
                Kysymys <%= i + 1%>: <%= kaikkiKysymykset.get(i).getKysymys()%><br>
            </div>
            

           <form method="POST" id="vastausformi"></br>
                <label>1</label><input type="radio" name="EVastaus" value="1" <% if (vastaus ==1){ out.println("checked='checked'");}%>/>
                <label>2</label><input type="radio" name="EVastaus" value="2" <% if (vastaus ==2){ out.println("checked='checked'");}%>/>
                <label>3</label><input type="radio" name="EVastaus" value="3" <% if (vastaus ==3){ out.println("checked='checked'");}%> />
                <label>4</label><input type="radio" name="EVastaus" value="4" <% if (vastaus ==4){ out.println("checked='checked'");}%>/>
                <label>5</label><input type="radio" name="EVastaus" value="5" <% if (vastaus ==5){ out.println("checked='checked'");}%>/>
                <input type="hidden" name="q" value="<%= i + 1%>"></br>
            </form>
              <%
                }
              %>



            <div id="muokkaaminen">
                <form method="POST">
                    <b>Muokkaaminen:</b></br>
                    Kysymys: 
                    <select name="kysymys">
                        <% for (int i = 0; i <= kaikkiKysymykset.size(); i++) {%>
                        <option value="<%= i%>"><%= i + 1%></option>
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

                    //response.setHeader("Refresh", "0; http://localhost:8080/vaalikone/Vaalikone?EVastaus=" + ehdokkaanVastaukset.get(19) + "&q=19");

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
