package com.example.robinson;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    String name;
    int food;
    int water;
    int materials;
    int daySurvived = 0;
    int workAmplifier, hungerAmplifier, thirstAmplifier, healthAmplifier;
    Map map;

    Character character;

    Mood currentMood;
    ArrayList<Mood> moodList;

    public Player(String name, Character character) {

        food = 100;
        water = 100;
        materials = 0;

        moodList = new ArrayList<>();
        moodList.add(new Mood("Счастлив", 140, 140));
        moodList.add(new Mood("Спокоен", 100, 100));
        moodList.add(new Mood("Грустный", 80, 90));
        moodList.add(new Mood("Разбитость", 50, 60));

        currentMood = new Mood("Спокоен", 100, 100);

        map = new Map();
        this.character = character;

        if (name.equals("dev")) {
            food = 9999;
            water = 9999;
            materials = 9999;
        }
        if (name.equals("")) this.name = "Новая игра";
        else
            this.name = name;
    }


    public void listDay() {
        daySurvived++;
        food -= 5 * (character.hungerAmplifier / 100);
        water -= 10 * (character.thirstAmplifier / 100);

        Random random = new Random();
        if ((food > 150 || water > 100) && random.nextInt(3) >= 1) {
            currentMood = moodList.get(0);
        }
        else if ((food > 100 || water > 75) && random.nextInt(3) >= 1) {
            currentMood = moodList.get(1);
            }
        else if ((food > 50 || water > 50) && random.nextInt(3) >= 1) {
            currentMood = moodList.get(2);
        }
        else if ((food > 25 || water > 15) && random.nextInt(3) >= 1) {
            currentMood = moodList.get(3);
        }

        workAmplifier = character.workAmplifier + currentMood.workAmplifier - 100;
        hungerAmplifier = character.hungerAmplifier;
        thirstAmplifier = character.thirstAmplifier;
        healthAmplifier = character.healthAmplifier + currentMood.healthAmplifier - 100;
    }

    void chooseAction(Action action) {
        food += action.changedFood * (character.hungerAmplifier / 100);
        water += action.changedWater * (character.thirstAmplifier / 100);
        materials += action.changedMaterials * (character.workAmplifier / 100);
    }

    String getFood() {
        if (food >= 100) return "Не хочет есть";
        if (food > 70) return "Легкий голод";
        if (food >= 20) return "Голод";
        return "При смерти";
    }

    String getWater() {
        if (water > 100) return "Не хочет пить";
        if (water > 70) return "Легкая жажда";
        if (water >= 20) return "Жажда";
        return "При смерти";
    }

        class Mood {
        String name;
        int workAmplifier, healthAmplifier;
            public Mood(String name, int workAmplifier, int healthAmplifier) {
                this.name = name;
                this.workAmplifier = workAmplifier;
                this.healthAmplifier = healthAmplifier;
            }
        }
}
