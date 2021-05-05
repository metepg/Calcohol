package com.example.projekti;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Luokka joka pitää sisällään instanssin lisätyistä päivätiedoista</h1>
 * Palauttaa sen hetkisen instanssin samana kaikkialle ohjelmassa
 *
 * @author Mete Güneysel
 * @version 1.0 5/2021
 */

public class Singleton {
    private List<DayInfo> days;
    private static final Singleton instance = new Singleton();

    /**
     * Palauttaa instanssin luokasta
     *
     * @return instanssi
     */
    public static Singleton getInstance() {
        return instance;
    }

    /**
     * Alustaa listan instanssille
     */
    private Singleton() {
        days = new ArrayList<>();
    }

    /**
     * Palauttaa instanssiin lisätyt tiedot listana
     *
     * @return lista joka sisältää DayInfo olioita
     */
    public List<DayInfo> getAllDays() {
        return days;
    }

    /**
     * Palauttaa halutun päivän tiedot
     *
     * @param i indeksi jonka mukaan tietoa haetaan listalta
     * @return palauttaa DayInfo olion
     */
    public DayInfo getOneDay(int i) {
        return days.get(i);
    }
}
