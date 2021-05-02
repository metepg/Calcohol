package com.example.projekti;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
    private List<DayInfo> days;
    private static final Singleton instance = new Singleton();

    public static Singleton getInstance(){
        return instance;
    }

    private Singleton(){
        days = new ArrayList<>();
    }

    public List<DayInfo> getAllDays(){
        return days;
    }

    public DayInfo getOneDay(int i) {
        return days.get(i);
    }
}
