/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vaalikone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author karoliina1506
 */
public class Ehdokas implements Serializable {
    private final List<Integer> EVastaus = new ArrayList<>(20);
 private final List<String> EKommentti = new ArrayList<>(20);
    private final static Logger logger = Logger.getLogger(Loki.class.getName());

    /**
     * Ehdokas-olioon tallennetaan vaalikoneen käyttäjän tietoja.
     */
    public Ehdokas() {

        //täytelläänhän listat valmiiksi
        for (int i = 0; i < 20; i++) {
            this.EVastaus.add(0);
            this.EKommentti.add("");
        }

    }

    /**
     *
     * @return Integer-lista ehdokkaan vastauksista
     */
    public List<Integer> getVastausLista() {
        return this.EVastaus;
    }

    /**
     * Hae yksittäinen ehdokkaan EVastaus kysymykseen
     *
     * @param index kysymyksen numero
     * @return Yksittäinen integer-muotoinen EVastaus ehdokkaan EVastaus-listasta
     */
    public Integer getVastaus(int index) {
        return this.EVastaus.get(index);
    }

    /**
     * Lisää EVastaus
     *
     * @param index kysymyksen numero
     * @param EVastaus vastauksen arvo
     */
    public void addVastaus(Integer index, Integer vastaus) {
        this.EVastaus.set(index, vastaus);
    }
    /**
     *
     * @return String-lista ehdokkaan kommenteista
     */
    public List<String> getKommenttiLista() {
        return this.EKommentti;
    }
    
        /**
     * Hae yksittäinen ehdokkaan EKommentti kysymykseen
     *
     * @param index kysymyksen numero
     * @return Yksittäinen integer-muotoinen EKommentti ehdokkaan
     * EKommentti-listasta
     */
    public String getKommentti(int index) {
        return this.EKommentti.get(index);
    }

    /**
     * Lisää EKommentti
     *
     * @param index kysymyksen numero
     * @param EKommentti vastauksen arvo
     */
    public void addKommentti(Integer index, String vastaus) {
        this.EKommentti.set(index, vastaus);
    }
}
