<%-- 
    Document   : KMuokkaus
    Created on : Apr 25, 2017, 2:58:59 PM
    Author     : roope1301
--%>

<%@page import="javax.persistence.Query"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="persist.Kysymykset" %>
<%@page session="true"%>

<%
    // Tarkistetaan onko "admin" -sessio olemassa ja jos ei niin ohjataan kirjautumiseen
    if (session.getAttribute("admin") != "admin") {
        request.getRequestDispatcher("AKirjautuminen.jsp")
                .forward(request, response);
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Kysymysten muokkaaminen</title>
        <link href="style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="container">
            <img id="headerimg" src="Logo.png" width="720" />
            <h1>Kysymysten muokkaaminen</h1>
            <%
                // Luodaan EntityManager -olio
                EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
                EntityManager em = emf.createEntityManager();

                // Haetaan kysymykset listaan tietokannasta
                Query q = em.createQuery(
                        "SELECT k FROM Kysymykset k ORDER BY k.kysymysId");
                List<Kysymykset> kaikkiKysymykset = q.getResultList();
            %> 
            <form>
                <b>Lista Kysymyksistä:</b></br>
                <select>
                    <%  // Listataan kaikki tietokannassa olevat kysymykset 
                        for (int i = 1; i <= kaikkiKysymykset.size(); i++) {%>
                    <option><%= kaikkiKysymykset.get(i - 1).getKysymysId()%>. <%= kaikkiKysymykset.get(i - 1).getKysymys()%></option>
                    <% }%>
                </select>
            </form></br>

            <form method="POST">
                <b>Lisää:</b></br>
                <!--Id: <input type="number" size ="3" name="lisaaid"/>-->
                Kysymys: <input type="text" maxlength="200" size="70" name="kysymys"/>
                <input type="submit" name="lisaa" value="Lisää" />
            </form></br>
            <%
                if (request.getParameter("lisaa") != null) {
                    try {
                        // Asetetaan uudelle kysymykselle ID:ksi ensimmäinen vapaa numero
                        int i = 1;
                        for (int j = 0; j < kaikkiKysymykset.size(); j++) {
                            if (kaikkiKysymykset.get(i - 1).getKysymysId() == i) {
                                i++;
                                out.println(i);
                            }
                        }

                        // Haetaan asetettu kysymys tekstikentästä
                        String kysymys = request.getParameter("kysymys");
                        
                        // Luodaan uusi kysymys entiteetti ja viedään se tietokantaan
                        Kysymykset kys = new Kysymykset(i);
                        kys.setKysymys(kysymys);
                        em.getTransaction().begin();
                        em.persist(kys);
                        em.getTransaction().commit();
                        response.setHeader("Refresh", "0; http://localhost:8080/vaalikone/KMuokkaus.jsp"); // Päivittää

                    } catch (Exception e) {
            %> Jokin meni vikaan, tarkista id. <%                    }
                }
            %>
            <form method="POST">
                <b>Muokkaa:</b></br>
                Id: <input type="number" size ="3" name="muokkaaid"/>
                Kysymys: <input type="text" maxlength="200" size="70" name="kysymys"/>
                <input type="submit" name="muokkaa" value="Päivitä" />
            </form></br>
            <%
                if (request.getParameter("muokkaa") != null) {
                    try {
                        // Haetaan tekstikentistä muokattavan kysymyksen Id ja uusi kysymys
                        String id = request.getParameter("muokkaaid");
                        String kysymys = request.getParameter("kysymys");
                        int i = Integer.parseInt(id);
                        
                        // Muokataan tietokannasta oikea entiteetti, muokataan sitä ja viedään takaisin tietokantaan
                        Kysymykset kys = em.find(Kysymykset.class, i);
                        em.getTransaction().begin();
                        kys.setKysymys(kysymys);
                        em.getTransaction().commit();
                        response.setHeader("Refresh", "0; http://localhost:8080/vaalikone/KMuokkaus.jsp"); // Päivittää
                    } catch (Exception e) {
            %> Jokin meni vikaan, tarkista id. <%                                }

                }
            %>
            <form>
                <b>Poista:</b></br>
                Id: <input type="number" size ="3" name="poistaid"/>
                <input type="submit" name="poista" value="Poista" />
            </form>
            <%
                if (request.getParameter("poista") != null) {
                    try {
                        // Haetaan tekstikentästä poistettavan kysymyksen ID
                        String id = request.getParameter("poistaid");
                        int i = Integer.parseInt(id);

                        // Haetaan haluttu kysymys tietokannasta ja poistetaan se sieltä
                        Kysymykset kys = em.find(Kysymykset.class, i);
                        em.getTransaction().begin();
                        em.remove(kys);
                        em.getTransaction().commit();
                        response.setHeader("Refresh", "0; http://localhost:8080/vaalikone/KMuokkaus.jsp");
                    } catch (Exception e) {
            %> Jokin meni vikaan, tarkista id. <%                                }

                }
            %></br>
            <a href="Admin.jsp">Takaisin</a>
        </div>
    </body>
</html>