package com.example.robinson;

import java.util.ArrayList;

public class Map {
    ArrayList<Location> locations = new ArrayList<>();
    Location currentLocation;

    public Map() {
    locations.add(new Location("Beach 1"));
    locations.add(new Location("Beach 2"));
    locations.add(new Location("Forest 1"));
    currentLocation = locations.get(0);
    }
}
class Location {
    String name;
    String description;

    int resourcesFood;
    int regeneratingFood;
    
    int resourcesWater;
    int regeneratingWater;

    int resourcesMaterials;
    int regenerationMaterials;

    public Location(String name, String description, int resourcesFood, int regeneratingFood, int resourcesWater, int regeneratingWater, int resourcesMaterials, int regenerationMaterials) {
        this.name = name;
        this.description = description;
        this.resourcesFood = resourcesFood;
        this.regeneratingFood = regeneratingFood;
        this.resourcesWater = resourcesWater;
        this.regeneratingWater = regeneratingWater;
        this.resourcesMaterials = resourcesMaterials;
        this.regenerationMaterials = regenerationMaterials;
    }

    public Location(String name) {
        this.name = name;
        this.description = "";
        this.resourcesFood = 1;
        this.regeneratingFood = 1;
        this.resourcesWater = 1;
        this.regeneratingWater = 1;
        this.resourcesMaterials = 1;
        this.regenerationMaterials = 1;
    }
}