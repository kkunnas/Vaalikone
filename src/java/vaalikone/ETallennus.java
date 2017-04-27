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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persist.Kysymykset;
import persist.Vastaukset;
import persist.VastauksetPK;

/**
 *
 * @author toni1523
 */
public class ETallennus extends HttpServlet {

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

        String tuloste;

        tuloste = "<html> "
                + "<head>"
                + "<link href='style.css' rel='stylesheet' type='text/css'>"
                + "</head>"
                + "<body>"
                + "<div id='container'>"
                + "<img id='headerimg' src='Logo.png' width='720' />"
                + "<div class='kysymys'>"
                + "<h1>";
        try {
            int ehdokas_id = (Integer) request.getAttribute("ehdokas_id");
            List<Integer> ehdokkaanVastaukset = (List<Integer>) request.getAttribute("ehdokkaanVastaukset");
            List<String> kommenttiLista = (List<String>) request.getAttribute("kommentti");

            List<Kysymykset> kaikkiKysymykset = (List<Kysymykset>) request.getAttribute("kaikkiKysymykset");

            EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
            EntityManager em = emf.createEntityManager();

            for (int i = 1; i <= kaikkiKysymykset.size(); i++) {

                // VastauksetPK vastauspk = new VastauksetPK(ehdokasID; kysymysID;
                VastauksetPK vastauspk = new VastauksetPK(ehdokas_id, i);

                // Vastaukset vastaus = new Vastaukset(String kommentti, int vastaus, VastauksetPK vastauksetPK);
                Vastaukset vastaus = new Vastaukset(kommenttiLista.get(i), ehdokkaanVastaukset.get(i), vastauspk);

                em.getTransaction().begin();
                em.persist(vastaus);
                em.getTransaction().commit();
            }

            em.close();
            tuloste += "Vastauksesi on tallennettu onnistuneesti!";

        } catch (Exception e) {
            tuloste += "Jotain meni vikaan, vastauksia ei tallennettu onnistuneesti!";

        } finally {
            tuloste += "</h1>"
                    + "Voit sulkea tämän välilehden"
                    + "<a href ='index.html'> Palaa etusivulle"
                    + "</div>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            out.println(tuloste);
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
