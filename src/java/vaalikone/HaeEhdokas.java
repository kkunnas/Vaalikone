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

        int ehdokas_id = Integer.parseInt(request.getParameter("ehdokas_id"));

        // Hae tietokanta-yhteys contextista
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        Query qId = em.createQuery(
                "SELECT e.ehdokasId FROM Ehdokkaat e");
        List<Integer> ehdokasIdList = qId.getResultList();

        if (request.getParameter("haeEhdokas") != null) {
            for (int i = 0; i < ehdokasIdList.size(); i++) {
                if (ehdokasIdList.get(i).equals(ehdokas_id)) {
                    request.setAttribute("ehdokas_id", ehdokas_id);
                    request.getRequestDispatcher("/Vaalikone")
                            .forward(request, response);
                }
            }
        // Tutkitaan löytyykö annettu ID tietokannasta ja jos löytyy ohjataan se EPoisto:lle
        } else if (request.getParameter("poistaEhdokas") != null) {
            for (int i = 0; i < ehdokasIdList.size(); i++) {
                if (ehdokasIdList.get(i).equals(ehdokas_id)) {
                    request.setAttribute("ehdokas_id", ehdokas_id);
                    request.getRequestDispatcher("/EPoisto")
                            .forward(request, response);
                }
            }

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
