package main.java;

import java.util.Vector;
import java.util.Map;

/**
 * HRInterface
 */
public interface HRInterface {
    /* Character = room type, Integer = available rooms left */
    Map<Character, Integer> listRooms();
    /* Accepts room type, returns room cost */
    int getRoomCost(char c);
    /* Books a room, if available. Accepts room type, number of rooms and the 
       guest's name. Returns true if successful otherwise false. */
    Boolean bookRoom(char type, int number, String name);
    /* Lists all guests */
    Vector<IGuest> listGuests();
    /* Cancels a reservation, if made. Accepts room type, number of rooms and 
       the guest's name. Returns true if successful otherwise false.  */
    Boolean cancelRoom(char type, int number, String name);
}