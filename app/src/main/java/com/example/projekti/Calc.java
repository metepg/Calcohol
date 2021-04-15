package com.example.projekti;

public class Calc {
    private int alcoholInBlood;
    private int burningTime;
    private int drinkAmount;
    private int startTime;

    // Constructor
    public Calc(){
        this.alcoholInBlood = 0;
        this.burningTime = 0;
        this.drinkAmount = 0;

    }

    public int getAlcoholInBlood() {
        return alcoholInBlood;
    }

    public int getBurningTime() {
        return burningTime;
    }

    public int getDrinkAmount() {
        return drinkAmount;
    }
}
