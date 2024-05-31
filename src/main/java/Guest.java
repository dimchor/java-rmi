package main.java;

import java.util.HashMap;
import java.util.Map;

public class Guest implements IGuest {
    public Guest(String name)
    {
        this.name = name;
        rooms = new HashMap<Character, Integer>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Character, Integer> getRooms() {
        return rooms;
    }

    public void put(Character character, Integer number) {
        rooms.put(character, number);
    }

    private String name;
    private Map<Character, Integer> rooms;
}
