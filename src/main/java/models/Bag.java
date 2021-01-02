package models;

import java.util.HashMap;

public class Bag {

    private String name;
    private int count;
    private final HashMap<Bag, Integer> bags = new HashMap<>();

    public Bag(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public Bag() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
