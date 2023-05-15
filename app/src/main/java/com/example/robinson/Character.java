package com.example.robinson;

public class Character {

    // Био
    String name;

    String profession;

    int age;

    // характеристики персонажа в процентах
    int workAmplifier;
    int hungerAmplifier;
    int thirstAmplifier;
    int healthAmplifier;

    public Character(String name, String profession, int age, int workAmplifier, int hungerAmplifier, int thirstAmplifier, int healthAmplifier) {
        this.name = name;
        this.profession = profession;
        this.age = age;
        this.workAmplifier = workAmplifier;
        this.hungerAmplifier = hungerAmplifier;
        this.thirstAmplifier = thirstAmplifier;
        this.healthAmplifier = healthAmplifier;
    }
}


