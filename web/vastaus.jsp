<%-- 
    Document   : vastaus
    Created on : 09-Apr-2015, 12:50:47
    Author     : Jonne
--%>

<%@page import="javax.persistence.Query"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,vaalikone.Vaalikone,persist.*"%>
<!doctype html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Diginide Vaalikone 2.0</title>

        <link href="style.css" rel="stylesheet" type="text/css">
    </head>

    <body>

        <div id="container">
            <img id="headerimg" src="images/Logo.png" width="500" height="144" alt=""/>



            <%
                @SuppressWarnings("unchecked")
                        // Luodaan EntityManager -olio
                EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
                EntityManager em = emf.createEntityManager();

                // Haetaan kysymykset listaan tietokannasta
                Query q = em.createQuery(
                        "SELECT k FROM Kysymykset k ORDER BY k.kysymysId");
                List<Kysymykset> kaikkiKysymykset = q.getResultList();
                List<Kysymykset> kysymykset = (List<Kysymykset>) request.getAttribute("kysymykset");
                for (Kysymykset kysymys : kysymykset) {%>

            <div class="kysymys">
                <%= kysymys.getKysymysId()%> / <%=kaikkiKysymykset.size() %><br>
                <%= kysymys.getKysymys()%>
            </div>
            <form action="Vaalikone" id="vastausformi">
                <label>1</label><input type="radio" name="vastaus" value="1" />
                <label>2</label><input type="radio" name="vastaus" value="2" />
                <label>3</label><input type="radio" name="vastaus" value="3" checked="checked" />
                <label>4</label><input type="radio" name="vastaus" value="4" />
                <label>5</label><input type="radio" name="vastaus" value="5" />
                <input type="hidden" name="q" value="<%= kysymys.getKysymysId()%>"></br>
                <input onclick="history.go(-1);
                        return true" type="button" id="seuraavanappi" value="Edellinen" />
                <input type="submit" id="seuraavanappi" value="Seuraava" />

            </form>
            <div class="kysymys"><small>1=Täysin eri mieltä 2=Osittain eri mieltä 3=En osaa sanoa, 4=Osittain samaa mieltä 5=Täysin samaa mieltä</small></div>
            <%
                }
            %>




        </div>

    </body>
</html>
