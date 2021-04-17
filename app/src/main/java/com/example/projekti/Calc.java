package com.example.projekti;

public class Calc {
    private int softAmount;
    private int strongAmount;
    private int wineAmount;
    private int liquorAmount;

    String gender;
    int weight;

    // Constructor
    // amount = alcohol amount as ml
    // gender = "woman" / "man"
    // weight = kg
    public Calc(int softAmount, int strongAmount, int wineAmount, int liquorAmount, String gender, int weight){
        this.softAmount = softAmount;
        this.strongAmount = strongAmount;
        this.wineAmount = wineAmount;
        this.liquorAmount = liquorAmount;
        this.gender = gender;
        this.weight = weight;
    }

    // Calculate alcohol level in blood
    // multiplier = ratio of body water to total weight (%)
    // Return level as ‰ e.g. 2.5
    public String getAlcoholInBlood() {
        double multiplier = gender == "man" ? 75 * weight : 66 * weight;
        double alcoholLevel = (double) Math.round((getAlcoholAmountAsGrams() / multiplier*10) *1000) /100;
        return alcoholLevel+"";
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

    // How long until alcohol is gone
    // Return as hours e.g. 2
    public String getBurningTime() {
        return Math.round(getAlcoholAmountAsGrams() / (weight/10))+"";
    }

    //lasketaan kalorit
    public int getCalories() {
        return (int) Math.round(getAlcoholAmountAsGrams() * 11);
    }
}
