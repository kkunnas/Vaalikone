<%-- 
    Document   : Etallennus
    Created on : Apr 21, 2017, 1:59:37 PM
    Author     : toni1523
--%>

<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.Query"%>
<%@page import="persist.Kysymykset"%>
<%@page import="persist.Vastaukset"%>
<%@page import="persist.VastauksetPK"%>
<%@page import="java.util.List"%>
<%@page import="persist.Ehdokkaat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet" type="text/css">
        <title>ETallennus</title>
    </head>
    <body>
        <div id="container">
            <%
                try {
                    int ehdokas_id = (Integer) request.getAttribute("ehdokas_id");
                    List<Integer> ehdokkaanVastaukset = (List<Integer>) request.getAttribute("ehdokkaanVastaukset");
                    EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
                    EntityManager em = emf.createEntityManager();

                    String kommentti = "Ehdokkaan vastaus kysymykseen numero ";

                    for (int i = 1; i < 20; i++) {

                        // TODO: Korvaa ehdokasID 
                        // VastauksetPK vastauspk = new VastauksetPK(ehdokasID; kysymysID;
                        VastauksetPK vastauspk = new VastauksetPK(ehdokas_id, i);

                        // Vastaukset vastaus = new Vastaukset(String kommentti, int vastaus, VastauksetPK vastauksetPK);
                        Vastaukset vastaus = new Vastaukset(kommentti + i, ehdokkaanVastaukset.get(i), vastauspk);

                        em.getTransaction().begin();
                        em.persist(vastaus);
                        em.getTransaction().commit();
                    }
                    %> Vastauksesi on tallennettu onnistuneesti! <%
   
                } catch (Exception e) {
            %> Jotain meni vikaan! Vastauksia ei tallennettu onnistuneesti <%
                }
            %>

        </div>


    </body>
</html>
