package com.example.robinson;

import java.util.Random;

public class Characters {

    Character[] characters;

    public Characters() {
        characters = new Character[] {
                new Character("Робин", "Программист", 25, 90, 90, 100, 90),
                new Character("Дмитрий", "Строитель", 46, 130, 120, 140, 110),
                new Character("Галя", "Пенсионерка", 72, 60, 60, 60, 50)
        };

    }

    public Character getCharacter() {
        Random random = new Random();
        return characters[random.nextInt(characters.length)];
    }
}
