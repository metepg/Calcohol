package com.example.projekti;

public class DayInfo {
    private int softAmount;
    private int strongAmount;
    private int wineAmount;
    private int liquorAmount;
    private String date;

    // Constructor
    // amount = alcohol amount as ml
    public DayInfo(String date){
        this.softAmount = 0;
        this.strongAmount = 0;
        this.wineAmount = 0;
        this.liquorAmount = 0;
        this.date = date;
    }

    public void addSoft(double amount){
        softAmount += amount;
        if(softAmount < 0){
            softAmount = 0;
        }
    }

    public void addStrong(double amount) {
        strongAmount += amount;
        if(strongAmount < 0){
            strongAmount = 0;
        }
    }

    public void addWine(double amount){
        wineAmount += amount;
        if(wineAmount < 0){
            wineAmount = 0;
        }
    }

    public void addLiquor(double amount) {
        liquorAmount += amount;
        if(liquorAmount < 0){
            liquorAmount = 0;
        }
    }

    public void reset() {
        softAmount = 0;
        strongAmount = 0;
        wineAmount = 0;
        liquorAmount = 0;
    }

    public String getDate(){
        return date;
    }

    public int getSoftAmount() {
        return softAmount;
    }

    public int getStrongAmount() {
        return strongAmount;
    }

    public int getWineAmount() {
        return wineAmount;
    }

    public int getLiquorAmount() {
        return liquorAmount;
    }

    public void setSoftAmount(int softAmount) {
        this.softAmount = softAmount;
    }

    public void setStrongAmount(int strongAmount) {
        this.strongAmount = strongAmount;
    }

    public void setWineAmount(int wineAmount) {
        this.wineAmount = wineAmount;
    }

    public void setLiquorAmount(int liquorAmount) {
        this.liquorAmount = liquorAmount;
    }

    // Calculate alcohol level in blood
    // multiplier = ratio of body water to total weight (%)
    // Return level as ‰ e.g. 2.5
    public String getAlcoholInBlood(User user) {
        double multiplier = (user.getGender() == "male" ? 75 : 66 ) * user.getWeight();
        double alcoholLevel = (double) Math.round((getAlcoholAmountAsGrams() / multiplier*10) *1000) /100;
        return alcoholLevel + "%";
    }

    // Return total alcohol amount as grams
    // Formula = alcohol content *(amount(ml)/100) * alcohol density
    public double getAlcoholAmountAsGrams() {
        double soft = 4.75 * (softAmount/100.0);
        double strong = 6.50 * (strongAmount/100.0);
        double wine = 12.50 * (wineAmount/100.0);
        double liquor = 40.00 * (liquorAmount/100.0);
        double total = (soft + strong + wine + liquor) * 0.79;
        return total;
    }

    // Return total amount of portions as a number
    public int getPortions() {
        double soft = softAmount / 330.0 ;
        double strong = strongAmount / 330.0;
        double wine = wineAmount / 120.0;
        double liquor = liquorAmount / 40.0;
        int total = (int) Math.round(soft + strong + wine + liquor);
        return total;
    }

    // How long until alcohol is gone
    // Return as hours e.g. 2
    public int getBurningTime(User user) {
        return (int) Math.ceil(getAlcoholAmountAsGrams() / (user.getWeight()/10));
    }

    //lasketaan kalorit
    public int getCalories() {
        return (int) Math.round(getAlcoholAmountAsGrams() * 7);
    }

   public String toString() {
        return getDate() + " (" + getDate() + ") portions";
    }
}
