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
import persist.Kysymykset;

/**
 *
 * @author karoliina1506
 */
public class VMuokkaus extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        int ehdokas_id = Integer.parseInt(request.getParameter("ehdokas_id"));
 
        boolean muokataanko=true;

        // Hae tietokanta-yhteys contextista
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        //hae ko. ehdokkaan vastaukset
        Query q = em.createQuery(
                "SELECT v.vastaus FROM Vastaukset v WHERE v.vastauksetPK.ehdokasId=?1");
        q.setParameter(1, ehdokas_id);
        List<Integer> ehdokkaanVastaukset = q.getResultList();
        ehdokkaanVastaukset.add(0,null);

        //hae kaikki kysymykset
        q = em.createQuery(
                "SELECT k FROM Kysymykset k");
        List<Kysymykset> kaikkiKysymykset = q.getResultList();
        
        request.setAttribute("ehdokas_id", ehdokas_id);
        request.setAttribute("muokataanko", muokataanko);
        request.setAttribute("ehdokkaanVastaukset", ehdokkaanVastaukset);
        request.setAttribute("kaikkiKysymykset", kaikkiKysymykset);
        request.getRequestDispatcher("/EListaus.jsp")
                .forward(request, response);
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
