package main.java;

import java.util.Vector;
import java.util.Map;

/**
 * HRInterface
 */
public interface HRInterface {
    Map<Character, Integer> listRooms();
    int getRoomCost(char c);
    Boolean bookRoom(char type, int number, String name);
    Vector<IGuest> listGuests();
}