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
 
    private final static Logger logger = Logger.getLogger(Loki.class.getName());

    /**
     * Ehdokas-olioon tallennetaan vaalikoneen käyttäjän tietoja.
     */
    public Ehdokas() {

        //täytelläänhän listat valmiiksi
        for (int i = 0; i < 20; i++) {
            this.EVastaus.add(0);
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
}
