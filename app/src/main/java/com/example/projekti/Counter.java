package com.example.projekti;

public class Counter {
    private int counter = 0;
    private int min;
    private int max;
    private int step;

    public Counter(){
        this.min = 0;
        this.max = 50;
        this.step = 1;
    }
    public String getCounter(){
        return Integer.toString(counter);
    }
    public int minus(){
        counter -= step;
        if(counter < min ) {
            return counter = min;
        }
        return counter;
    }
    public int add(){
        counter += step;
        if(counter > max){
            return counter = max;
        }
        return counter;
    }
    public int reset(){
        return 0;
    }
}
