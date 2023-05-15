package com.example.robinson;

public class Map {
    Location[] map;


}
class Location {
    String name;
    String description;

    // Кол-во ресурса на местности / сколько прибывает за день
    int resourcesFood;
    int regeneratingFood;
    
    int resourcesWater;
    int regeneratingWater;

    int resourcesMaterials;
    int regenerationMaterials;

}