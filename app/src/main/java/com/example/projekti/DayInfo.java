package com.example.projekti;

/**
 * <h1>Luokka johon tallennetaan käyttäjän lisäämiä tietoja juotujen juomien määrästä</h1>
 * <p>Käyttäjä voi tallentaa tämän luokan instansseja puhelimen tiedostoon</p>
 * <p>Instansseja käytetään sovelluksessa tiedonlähteenä</p>
 *
 * @author Mete Güneysel
 * @version 1.0 5/2021
 */
public class DayInfo {
    private int softAmount;
    private int strongAmount;
    private int wineAmount;
    private int liquorAmount;
    private String date;

    /**
     * Luokan konstruktori
     *
     * @param date päivämäärä muodossa ("yyyy,MM,dd") esim. 2021-05-22
     */
    public DayInfo(String date) {
        this.softAmount = 0;
        this.strongAmount = 0;
        this.wineAmount = 0;
        this.liquorAmount = 0;
        this.date = date;
    }

    /**
     * Päivittää lisättyjen <strong>mietojen</strong> juomien määrän (minimiarvo 0)
     *
     * @param amount juoman määrä kokonaislukuna millilitroissa
     */
    public void updateSoft(double amount) {
        softAmount += amount;
        if (softAmount < 0) {
            softAmount = 0;
        }
    }

    /**
     * Päivittää lisättyjen <strong>vahvojen</strong> juomien määrän (minimiarvo 0)
     *
     * @param amount juoman määrä millilitroissa
     */
    public void updateStrong(double amount) {
        strongAmount += amount;
        if (strongAmount < 0) {
            strongAmount = 0;
        }
    }

    /**
     * Päivittää lisätyn <strong>viinin</strong> määrän (minimiarvo 0)
     *
     * @param amount juoman määrä millilitroissa
     */
    public void updateWine(double amount) {
        wineAmount += amount;
        if (wineAmount < 0) {
            wineAmount = 0;
        }
    }

    /**
     * Päivittää lisätyn <strong>viinan</strong> määrän (minimiarvo 0)
     *
     * @param amount juoman määrä millilitroissa
     */
    public void updateLiquor(double amount) {
        liquorAmount += amount;
        if (liquorAmount < 0) {
            liquorAmount = 0;
        }
    }

    /**
     * Palauttaa päivämäärän
     *
     * @return päivämäärä muodossa ("yyyy-MM-dd") esim. 2021-03-29
     */
    public String getDate() {
        return date;
    }

    /**
     * Palauttaa mietojen juomien määrän
     *
     * @return mietojen juomien määrä millilitroina
     */
    public int getSoftAmount() {
        return softAmount;
    }

    /**
     * Palaluttaa vahvojen juomien määrän
     *
     * @return vahvojen juomien määrä millilitroina
     */
    public int getStrongAmount() {
        return strongAmount;
    }

    /**
     * Palauttaa viinin määrän
     *
     * @return viinin määrä millilitroina
     */
    public int getWineAmount() {
        return wineAmount;
    }

    /**
     * Palauttaa viinan määrän
     *
     * @return viinan määrä millilitroina
     */
    public int getLiquorAmount() {
        return liquorAmount;
    }

    /**
     * Palauttaa mietojen juomien annosmäärän
     *
     * @return annosmäärä tekstinä esim "2"
     */
    public String getSoftPortions() {
        return String.valueOf(softAmount / 330);
    }

    /**
     * Palauttaa vahvojen juomien annosmäärän
     *
     * @return annosmäärä tekstinä esim "2"
     */
    public String getStrongPortions() {
        return String.valueOf(strongAmount / 220);
    }

    /**
     * Palauttaa viinin annosmäärät
     *
     * @return annosmäärä tekstinä esim "2"
     */
    public String getWinePortions() {
        return String.valueOf(wineAmount / 120);
    }

    /**
     * Palauttaa viinan annosmäärät
     *
     * @return annosmäärä tekstinä esim "2"
     */
    public String getLiquorPortions() {
        return String.valueOf(liquorAmount / 40);
    }

    /**
     * Asettaa mietojen juomien määrän
     *
     * @param softAmount määrä millilitroina
     */
    public void setSoftAmount(int softAmount) {
        this.softAmount = softAmount;
    }

    /**
     * Asettaa vahvojen juomien määrän
     *
     * @param strongAmount määrä millilitroina
     */
    public void setStrongAmount(int strongAmount) {
        this.strongAmount = strongAmount;
    }

    /**
     * Asettaa viinin juomien määrän
     *
     * @param wineAmount määrä millilitroina
     */
    public void setWineAmount(int wineAmount) {
        this.wineAmount = wineAmount;
    }

    /**
     * Asettaa viinan juomien määrän
     *
     * @param liquorAmount määrä millilitroina
     */
    public void setLiquorAmount(int liquorAmount) {
        this.liquorAmount = liquorAmount;
    }

    /**
     * Laskee alkoholin määrän veressä
     * multiplier = nesteen määrä kehossa, suhteessa painoon
     *
     * @param user User-olio, mistä luetaan käyttäjän sukupuoli ja paino
     * @return palauttaa alkoholin määrän veressä
     */
    public String getAlcoholInBlood(User user) {
        double multiplier = (user.getGender().equals("male") ? 75 : 66) * user.getWeight();
        double alcoholLevel = (double) Math.round((getAlcoholAmountAsGrams() / multiplier * 10) * 1000) / 100;
        return alcoholLevel + "%";
    }

    /**
     * Palauttaa alkoholin määrän grammoina
     *
     * @return alkoholin määrä grammoina
     */
    public double getAlcoholAmountAsGrams() {
        double soft = 4.75 * (softAmount / 100.0);
        double strong = 6.50 * (strongAmount / 100.0);
        double wine = 12.50 * (wineAmount / 100.0);
        double liquor = 40.00 * (liquorAmount / 100.0);
        return (soft + strong + wine + liquor) * 0.97;
    }

    /**
     * Laskee ja palauttaa päivän juotujen annosten määrän
     *
     * @return juotujen annosten määrä kokonaislukuna
     */
    public int getPortions() {
        int soft = softAmount / 330;
        int strong = strongAmount / 220;
        int wine = wineAmount / 120;
        int liquor = liquorAmount / 40;
        return Math.round(soft + strong + wine + liquor);
    }

    /**
     * Laskee ja palauttaa tuntimäärän kauan alkoholilla kestää poistua kehosta
     *
     * @param user User-olio mistä löytyy käyttäjän paino
     * @return palamisen kesto tunteina, esim 2
     */
    public int getBurningTime(User user) {
        return (int) (getAlcoholAmountAsGrams() / (user.getWeight() / 10));
    }

    /**
     * Laskee juotujen juomien kalorit yhteen
     *
     * @return palauttaa kalorit kokonaislukuna
     */
    public int getCalories() {
        return (int) Math.round(getAlcoholAmountAsGrams() * 9);
    }
}

