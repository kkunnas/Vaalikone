/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vaalikone;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persist.Vastaukset;

/**
 *
 * @author karoliina1506
 */
public class HaeEhdokas extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("admin") != "admin") {
            request.getRequestDispatcher("AKirjautuminen.jsp")
                    .forward(request, response);
        }

        try {
            int ehdokas_id = Integer.parseInt(request.getParameter("ehdokas_id"));

            // Hae tietokanta-yhteys contextista
            EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
            EntityManager em = emf.createEntityManager();

            Query qId = em.createQuery(
                    "SELECT e.ehdokasId FROM Ehdokkaat e");
            List<Integer> ehdokasIdList = qId.getResultList();


            for (int i = 0; i < ehdokasIdList.size(); i++) {
                if (ehdokasIdList.get(i).equals(ehdokas_id)) {

                    //hae listaan ehdokkaiden ID:t vastaukset taulusta
                    Query qV = em.createQuery(
                            "SELECT v.vastauksetPK.ehdokasId FROM Vastaukset v WHERE v.vastauksetPK.ehdokasId=?1");
                    qV.setParameter(1, ehdokas_id);
                    List<Integer> vastausList = qV.getResultList();


                    request.setAttribute("ehdokas_id", ehdokas_id);

                    if (request.getParameter("haeEhdokas") != null) {
                        while (vastausList.size() > ehdokas_id) {
                            //Tutkitaan vastaako vastaukset taulun ehdokasId parametrinä tuotua ehdokas_id:tä
                            if (vastausList.get(i).equals(ehdokas_id)) {
                                //Jos vastaa, siirrytään muokkaamaan tietokantaan tallennettuja tietoja
                                request.getRequestDispatcher("/VMuokkaus")
                                        .forward(request, response);
                            }
                        }
                        request.setAttribute("ehdokas_id", ehdokas_id);
                        request.getRequestDispatcher("/Vaalikone")
                                .forward(request, response);

                    } else if (request.getParameter("poistaEhdokas") != null) {

                        request.setAttribute("ehdokas_id", ehdokas_id);
                        request.getRequestDispatcher("/EPoisto")
                                .forward(request, response);
                    }

                }
            }

        } catch (Exception e) {
            String error = "Syötä vain yksi kokonaisluku";
            request.setAttribute("viesti", error);
            request.getRequestDispatcher("Admin.jsp")
                    .forward(request, response);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}