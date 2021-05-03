package com.example.projekti;

/**
 * Käyttäjän luokka
 *
 * @author teme
 * @version 1.0 5/2021
 */
public class User {
    private int age;
    private String gender;
    private int weight;

    /**
     * Konstruktori
     *
     * @param age    määrittää käyttäjän iän, esim. 18
     * @param gender määrittää käyttäjän sukupuolen, esim. "female"
     * @param weight määrittää käyttäjän painon, esim 80
     */
    public User(int age, String gender, int weight) {
        this.age = age;
        this.gender = gender;
        this.weight = weight;
    }

    /**
     * @return palauttaa sukupuolen
     */
    public String getGender() {
        return gender;
    }

    /**
     * @return palauttaa painon2
     */
    public int getWeight() {
        return weight;
    }

}
