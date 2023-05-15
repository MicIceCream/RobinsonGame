package com.example.robinson;

public class Player {
    String name;
    int food;
    int water;
    int materials;
    int daySurvived = 0;


    Characters characters = new Characters();
    Character character = characters.getCharacter();

    public Player(String name) {


        food = 100;
        water = 100;
        materials = 0;


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


}
