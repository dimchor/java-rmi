package main.java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * HRImplementation
 */
public class HRImplementation implements HRInterface {
    final static int ROOM_TYPE_A_NUMBER = 40;
    final static int ROOM_TYPE_B_NUMBER = 35;
    final static int ROOM_TYPE_C_NUMBER = 25;
    final static int ROOM_TYPE_D_NUMBER = 30;
    final static int ROOM_TYPE_E_NUMBER = 20;

    final static int ROOM_TYPE_A_COST = 75;
    final static int ROOM_TYPE_B_COST = 110;
    final static int ROOM_TYPE_C_COST = 120;
    final static int ROOM_TYPE_D_COST = 150;
    final static int ROOM_TYPE_E_COST = 200;


    private Map<Character, Integer> rooms;
    private Map<String, Map<Character, Integer>> guests;

    public HRImplementation() {
        rooms = new HashMap<Character, Integer>(5);
        rooms.put('A', ROOM_TYPE_A_NUMBER);
        rooms.put('B', ROOM_TYPE_B_NUMBER);
        rooms.put('C', ROOM_TYPE_C_NUMBER);
        rooms.put('D', ROOM_TYPE_D_NUMBER);
        rooms.put('E', ROOM_TYPE_E_NUMBER);

        guests = new HashMap<String, Map<Character, Integer>>();
    }

    public Map<Character, Integer> listRooms() {
        return rooms;
    }

    public int getRoomCost(char c) throws Exception {
        switch (c) {
            case 'A':
                return ROOM_TYPE_A_COST;
            case 'B':
                return ROOM_TYPE_B_COST;
            case 'C':
                return ROOM_TYPE_C_COST;
            case 'D':
                return ROOM_TYPE_D_COST;
            case 'E':
                return ROOM_TYPE_E_COST;
            default:
                throw new Exception("invalid room type");
        }
    }

    public Pair<Integer, Integer> bookRoom(char type, int number, String name) 
        throws Exception {

        if (rooms.get(type) < number)
            throw new Exception("not enough rooms of this type (" + 
                rooms.get(type) + " < " + number + ")");
        if (!guests.containsKey(name))
            guests.put(name, new HashMap<Character, Integer>());
        guests.get(name).put(type, number);
        return new Pair<Integer,Integer>(number, getRoomCost(type) * number);
    }

    public Set<String> listGuests() {
        Set<String> guestSet = new HashSet<String>();
        for(var entrySet: guests.entrySet())
            guestSet.add(entrySet.getKey());
        return guestSet;
    }

    public Map<Character, Integer> cancelRoom(char type, int number, 
        String name) throws Exception {

        if (!guests.containsKey(name))
            throw new Exception("guest not found");

        if (!guests.get(name).containsKey(type))
            throw new Exception("guest hasn't book such room type");

        int booked_rooms = guests.get(name).get(type);

        if (booked_rooms < number)
            throw new Exception("guest has only booked " + booked_rooms + 
                "rooms");
        
        guests.get(name).put(type, booked_rooms -= number);
        
        if (booked_rooms == 0) {
            guests.get(name).remove(type);
            if (guests.get(name).isEmpty());
                guests.remove(name);
        }

        if (guests.containsKey(name))
            return guests.get(name);
        else
            return null;
    }
}