package com.example.projekti;

public class User {
    private int age;
    private String gender;
    private int weight;

    // Constructor
    public User(int age, String gender, int weight){
        this.age = age;
        this.gender = gender;
        this.weight = weight;
    }
    public int getAge(){
        return age;
    }
    public String getGender(){
        return gender;
    }
    public int getWeight(){
        return weight;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public void setWeight(int weight) {
        this.weight = weight;
    }
}
