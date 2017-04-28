 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaalikone;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persist.Ehdokkaat;
import persist.Kysymykset;
import persist.Vastaukset;

/**
 * Vaalikone-servlet, vastaa vaalikoneen varsinaisesta toiminnallisuudesta
 *
 * @author Jonne
 */
public class Vaalikone extends HttpServlet {

    //Alustukset
    private final static Logger logger = Logger.getLogger(Loki.class.getName());
    private Ehdokas ehdokas;
    private Kayttaja usr;
    private int ehdokas_id;
    private int kysymys_id;

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
      
        // Hae tietokanta-yhteys contextista
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        //Jos etusivun Ehdokas buttonia on klikattu
        if (request.getParameter("haeEhdokas") != null) {
            //Käytetään ehdokkaan puolta, joten asetetaan Käyttäjä olion arvoksi null
            usr = null;
            //hae http-sessio ja luo uusi jos vanhaa ei ole vielä olemassa
            HttpSession session = request.getSession(true);
            ehdokas = (Ehdokas) session.getAttribute("e");
            
            //Tallennetaan muuttujaan attribuuttina saatu arvo
            ehdokas_id = (Integer) request.getAttribute("ehdokas_id");

            //jos ehdokas-oliota ei löydy sessiosta, luodaan sinne sellainen
            if (ehdokas == null) {
                ehdokas = new Ehdokas(haeKysymykset(em).size()+1); //Ehdokas luokan rakentaja alustaa listat oikean kokoisiksi
                logger.log(Level.FINE, "Luotu uusi ehdokas-olio");
                session.setAttribute("e", ehdokas);
            }
            //Jos etusivun Käyttäjä buttonia on klikattu
        } else if (request.getParameter("Kayttaja") != null) {
            //Käytetään käyttäjän puolta, joten asetetaan Ehdokas olion arvoksi null
            ehdokas = null;
            //hae http-sessio ja luo uusi jos vanhaa ei ole vielä olemassa
            HttpSession session2 = request.getSession(true);
            //hae käyttäjä-olio http-sessiosta
            usr = (Kayttaja) session2.getAttribute("usrobj");

            //jos käyttäjä-oliota ei löydy sessiosta, luodaan sinne sellainen
            if (usr == null) {
                usr = new Kayttaja(haeKysymykset(em).size()+1); //Käyttäjä luokan rakentaja alustaa listat oikean kokoisiksi
                logger.log(Level.FINE, "Luotu uusi käyttäjä-olio");
                session2.setAttribute("usrobj", usr);
            }
        }

        //hae url-parametri func joka määrittää toiminnon mitä halutaan tehdä.
        //func=haeEhdokas: hae tietyn ehdokkaan tiedot ja vertaile niitä käyttäjän vastauksiin
        String strFunc = request.getParameter("func");

        //Jos strFunc ei määritelty, esitetään kysymyksiä.
        if (strFunc == null) {

            //hae parametrinä tuotu edellisen kysymyksen nro
            String strKysymys_id = request.getParameter("q");
            String kommentti = null;
            String strVastaus = null;

            //Jos ehdokas olio ei ole null, hae parametrina tuotu edellisen kysymyksen vastaus ja kommentti
            if (ehdokas != null) {
                strVastaus = request.getParameter("EVastaus");
                kommentti = request.getParameter("kommentti");
            } 
            //Jos käyttäjä olio ei ole null, hae parametrina tuotu edellisen kysymyksen vastaus
            else if (usr != null) {
                strVastaus = request.getParameter("vastaus");
            }

            // Jos kysymyksen numero (kysId) on asetettu, haetaan tuo kysymys
            // muuten haetaan kysnro 1
            if (strKysymys_id == null) {
                kysymys_id = 1;
            } else {
                kysymys_id = parseInt(strKysymys_id);
                //jos vastaus on asetettu, tallenna se session käyttäjä-olioon
                if (strVastaus != null) {
                    //Jos ehdokas olio ei ole null, tallenetaan vastaus ja kysymys session ehdokas-olioon
                    if (ehdokas != null) {
                        ehdokas.addVastaus(kysymys_id, parseInt(strVastaus));
                        ehdokas.addKommentti(kysymys_id, kommentti);
                    } 
                    //Jos käyttäjä olio ei ole null, tallenetaan vastaus session käyttäjä-olioon
                    else if (usr != null) {
                        usr.addVastaus(kysymys_id, parseInt(strVastaus));
                    }
                }

                //määritä seuraavaksi haettava kysymys
                kysymys_id++;
            }

            //jos kysymyksiä on vielä jäljellä, hae seuraava
            if (haeKysymykset(em).size() >= kysymys_id) {
                try {
                    //Hae haluttu kysymys tietokannasta
                    Query q = em.createQuery(
                            "SELECT k FROM Kysymykset k WHERE k.kysymysId=?1");
                    q.setParameter(1, kysymys_id);
                    //Lue haluttu kysymys listaan
                    List<Kysymykset> kysymysList = q.getResultList();
                    request.setAttribute("kysymykset", kysymysList);

                     //Jos ehdokas olio ei ole null, ohjataan ehdokas EVastaus.jsp sivulle vastaamaan kysymyksiin
                    if (ehdokas != null) {
                        request.getRequestDispatcher("/EVastaus.jsp")
                                .forward(request, response);
                    } 
                    //Jos käyttäjä olio ei ole null, ohjataan käyttäjä vastaus.jsp sivulle vastaamaan kysymyksiin
                    else if (usr != null) {
                        request.getRequestDispatcher("/vastaus.jsp")
                                .forward(request, response);
                    }

                } finally {
                    // Sulje tietokantayhteys
                    if (em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }
                    em.close();
                }

                //jos kysymykset loppuvat, lasketaan tulos!
            } else {

                if (ehdokas == null) {

                    //Tyhjennetään piste-array jotta pisteet eivät tuplaannu mahdollisen refreshin tapahtuessa
                    for (int i = 0; i < haeKysymykset(em).size(); i++) {
                        usr.pisteet.set(i, new Tuple<>(0, 0));
                    }

                    //Hae lista ehdokkaista
                    Query qE = em.createQuery(
                            "SELECT e.ehdokasId FROM Ehdokkaat e");
                    List<Integer> ehdokasList = qE.getResultList();

                    //iteroi ehdokaslista läpi
                    for (int i = 1; i < ehdokasList.size(); i++) {

                        //Hae lista ehdokkaiden vastauksista
                        Query qV = em.createQuery(
                                "SELECT v FROM Vastaukset v WHERE v.vastauksetPK.ehdokasId=?1");
                        qV.setParameter(1, i);
                        List<Vastaukset> vastausList = qV.getResultList();

                        //iteroi vastauslista läpi
                        for (Vastaukset eVastaus : vastausList) {
                            int pisteet;

                            //hae käyttäjän ehdokaskohtaiset pisteet
                            pisteet = usr.getPisteet(i);

                            //laske oman ja ehdokkaan vastauksen perusteella pisteet 
                            pisteet += laskePisteet(usr.getVastaus(i), eVastaus.getVastaus());

                            logger.log(Level.INFO, "eID: {0} / k: {1} / kV: {2} / eV: {3} / p: {4}", new Object[]{i, eVastaus.getVastauksetPK().getKysymysId(), usr.getVastaus(i), eVastaus.getVastaus(), pisteet});
                            usr.addPisteet(i, pisteet);
                        }

                    }

                    //siirrytään hakemaan paras ehdokas
                    strFunc = "haeEhdokas";
                } else {
                    // Ohjataan tiedot vastauksien listaus sivulle
                    request.setAttribute("ehdokas_id", ehdokas_id);
                    
                    // Ohjataan tiedot vastauksien listaus sivulle
                    request.setAttribute("ehdokas_id", ehdokas_id);
                    request.setAttribute("kommentti", ehdokas.getKommenttiLista()); //kaikki ehdokkaan kommentit Ehdokas-luokan listasta
                    request.setAttribute("ehdokkaanVastaukset", ehdokas.getVastausLista()); // kaikki ehdokkaan vastaukset Ehdokas-luokan listasta
                    request.setAttribute("kaikkiKysymykset", haeKysymykset(em)); //kaikki kysymykset tietokannasta
                    request.getRequestDispatcher("/EListaus.jsp") //Uudelleen ohjataan EListaus.jsp sivulle
                            .forward(request, response);
                }
            }

        }

        //jos func-arvo on haeEhdokas, haetaan haluttu henkilö käyttäjälle sopivimmista ehdokkaista
        if ("haeEhdokas".equals(strFunc)) {
            //luetaan url-parametristä "top-listan järjestysnumero". Jos ei määritelty, haetaan PARAS vaihtoehto.
            String strJarjestysnumero = request.getParameter("numero");
            Integer jarjestysnumero = 0;
            if (strJarjestysnumero != null) {
                jarjestysnumero = Integer.parseInt(strJarjestysnumero);
            }

            //Lue käyttäjälle sopivimmat ehdokkaat väliaikaiseen Tuple-listaan.
            List<Tuple<Integer, Integer>> tpl = usr.haeParhaatEhdokkaat();

            //hae määritetyn ehdokkaan tiedot
            Query q = em.createQuery(
                    "SELECT e FROM Ehdokkaat e WHERE e.ehdokasId=?1");
            q.setParameter(1, tpl.get(jarjestysnumero).ehdokasId);
            List<Ehdokkaat> parasEhdokas = q.getResultList();

            //hae ko. ehdokkaan vastaukset
            q = em.createQuery(
                    "SELECT v FROM Vastaukset v WHERE v.vastauksetPK.ehdokasId=?1");
            q.setParameter(1, tpl.get(jarjestysnumero).ehdokasId);
            List<Vastaukset> parhaanEhdokkaanVastaukset = q.getResultList();

            //hae kaikki kysymykset
            q = em.createQuery(
                    "SELECT k FROM Kysymykset k");
            List<Kysymykset> kaikkiKysymykset = q.getResultList();

            //ohjaa tiedot tulosten esityssivulle
            request.setAttribute("kaikkiKysymykset", kaikkiKysymykset);
            request.setAttribute("kayttajanVastaukset", usr.getVastausLista());
            request.setAttribute("parhaanEhdokkaanVastaukset", parhaanEhdokkaanVastaukset);
            request.setAttribute("parasEhdokas", parasEhdokas);
            request.setAttribute("pisteet", tpl.get(jarjestysnumero).pisteet);
            request.setAttribute("jarjestysnumero", jarjestysnumero);
            request.getRequestDispatcher("/tulokset.jsp")
                    .forward(request, response);

            // Sulje tietokantayhteys
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();

        }

    }
                    
    // Haetaan kaikki kysymykset tietokannasta
    private List haeKysymykset(EntityManager em) {
        Query q = em.createQuery(
                "SELECT k FROM Kysymykset k");
        List<Kysymykset> kaikkiKysymykset = q.getResultList();
        return kaikkiKysymykset;
    }

    private Integer laskePisteet(Integer kVastaus, Integer eVastaus) {
        int pisteet = 0;
        if (kVastaus - eVastaus == 0) {
            pisteet = 3;
        }
        if (kVastaus - eVastaus == 1 || kVastaus - eVastaus == -1) {
            pisteet = 2;
        }
        if (kVastaus - eVastaus == 2 || kVastaus - eVastaus == -2 || kVastaus - eVastaus == 3 || kVastaus - eVastaus == -3) {
            pisteet = 1;
        }

        //if (kVastaus - eVastaus == 4 || kVastaus - eVastaus == -4) pisteet = 0;
        return pisteet;

    }

    //<editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
