package com.example.robinson;


public class Day {

    int stage = -1;
    Actions actions = new Actions();
    Action action1, action2, action3, skipAction;

    void nextStage(Player player) {
        action1 = actions.getRandomAction();
        action2 = actions.getRandomAction();
        action3 = actions.getRandomAction();
        skipAction = new Action("", "Вместо работы вы решаете отдохнуть и собраться с мыслями", 0, 0,0 );



        if (player.materials >= 125) {
            action1 = new Action("Построить лодку", "Долой остров! Вы собираете небольшой плот из своих ресурсов и наконец уплываете в закат!", 0, 0, -125);
        }

        while (action2.name.equals(action1.name) || action2.name.equals(action3.name)) {
            action2 = actions.getRandomAction();
        }
        while (action3.name.equals(action1.name) || action3.name.equals(action2.name)) {
            action3 = actions.getRandomAction();
        }
        if (stage == 1) {
            stage = 0;
            player.listDay();
        }
        else stage++;
    }





}
