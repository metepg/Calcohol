package com.example.projekti;

public class Counter {
    private int counter = 0;
    private int min;
    private int max;
    private int step;
    private int big;
    private int small;

    public Counter(){
        this.min = 0;
        this.max = 50;
        this.step = 1;
        this.big = 0;
        this.small = 0;
    }
    public String getCounter(){
        return Integer.toString(counter);
    }

    // Jos kutsutaan + merkillä lisää 1
    // Muuten vähennä 1
    public void setBig(String prefix) {
        if(prefix == "+") {
            big++;
            return;
        }
        big--;
        if(big < min){
            big=0;
        }
    }

    // Jos kutsutaan + merkillä lisää 1
    // Muuten vähennä 1
    public void setSmall(String prefix) {
        if(prefix == "+"){
            small++;
            return;
        }
        small--;
        if(small < 0){
            small=0;
        }
    }

    public String getBig() {
        return String.valueOf(big);
    }

    public String getSmall() {
        return String.valueOf(small);
    }

    public int reset(){
        return 0;
    }
}
