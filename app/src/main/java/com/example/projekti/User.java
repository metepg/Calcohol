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
     * Palauttaa iän tekstinä
     * @return ikä esim. 55
     */
    public String getAge() {
        return String.valueOf(age);
    }

    /**
     * Palauttaa sukupuolen
     * @return sukupuoli esim. "male"
     */
    public String getGender() {
        return gender;
    }

    /**
     * Palauttaa painon numerona
     * @return paino esim 55
     */
    public int getWeight() {
        return weight;
    }

}
