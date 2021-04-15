package com.example.projekti;

public class User {
    private int age;
    private String gender;
    private int weight;
    private int height;

    // Constructor
    public User(int age, String gender, int weight, int height){
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
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
    public int getHeight(){
        return height;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
