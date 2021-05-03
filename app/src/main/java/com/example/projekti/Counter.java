package com.example.projekti;

/**
 * Luokka joka laskee lisättävien juomien määrää
 *
 * @author Mete Güneysel
 * @version 1.0 5/2021
 */
public class Counter {
    private int min;
    private int big;
    private int small;

    /**
     * Luokan konstruktori
     */
    public Counter() {
        this.min = 0;
        this.big = 0;
        this.small = 0;
    }

    /**
     * Kasvattaa tai vähentää muuttujan <strong>big</strong> arvoa yhdellä.
     * Muuttujan arvo ei voi mennä alle 0
     *
     * @param prefix, määrittelee laskutoimituksen (+ tai -)
     */
    public void setBig(String prefix) {
        if (prefix == "+") {
            big++;
            return;
        }
        big--;
        if (big < min) {
            big = 0;
        }
    }

    /**
     * Kasvattaa tai vähentää muuttujan <strong>small</strong> arvoa yhdellä.
     * Muuttujan arvo ei voi mennä alle 0
     *
     * @param prefix, määrittelee laskutoimituksen (+ tai -)
     */
    public void setSmall(String prefix) {
        if (prefix == "+") {
            small++;
            return;
        }
        small--;
        if (small < 0) {
            small = 0;
        }
    }

    /**
     * @return palauttaa muuttujan <strong>big</strong> senhetkisen arvon tekstinä
     */
    public String getBig() {
        return String.valueOf(big);
    }

    /**
     * @return palauttaa muuttujan <strong>small</strong> senhetkisen arvon tekstinä
     */
    public String getSmall() {
        return String.valueOf(small);
    }

    /**
     * Nollaa laskurin arvot
     */
    public void reset() {
        small = 0;
        big = 0;
    }
}
