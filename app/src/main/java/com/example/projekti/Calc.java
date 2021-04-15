package com.example.projekti;

public class Calc {
    private double softVal;
    private double strongVal;
    private double wineVal;
    private double liquorVal;

    String gender;
    int weight;

    // Constructor
    public Calc(int softAmount, int strongAmount, int wineAmount, int liquorAmount, String gender, int weight){
        this.softVal = 4.75 * (softAmount/100.0) * 0.79;
        this.strongVal = 6.50 * (strongAmount/100.0) * 0.79;
        this.wineVal = 12.50 * (wineAmount/100.0) * 0.79;
        this.liquorVal = 40.00 * (liquorAmount/100.0) * 0.79;
        this.gender = gender;
        this.weight = weight;
    }

    public String getAlcoholInBlood() {
        double kerroin = gender == "man" ? 0.75 * weight : 0.66 * weight;
        double amount = softVal + strongVal + wineVal + liquorVal;
        return Math.round(amount / kerroin * 100.0) / 100.0+"";
    }

    public String getBurningTime() {
        return Math.round(softVal / (weight/10))+"";
    }

    public int getCalories() {
        return 0;
    }

}
