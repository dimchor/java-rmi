package main.java;

import java.util.Map;
import java.util.Set;

/**
 * HRInterface
 */
public interface HRInterface {
    /* Character = room type, Integer = available rooms left */
    Map<Character, Integer> listRooms();
    /* Accepts room type, returns room cost */
    int getRoomCost(char c) throws Exception;
    /* Books a room, if available. Accepts room type, number of rooms and the 
       guest's name. Returns the booked rooms and the total cost. Throws 
       Exception if not found. */
    Pair<Integer, Integer> bookRoom(char type, int number, String name) 
      throws Exception;
    /* Lists all guests */
    Set<String> listGuests();
    /* Cancels a reservation, if made. Accepts room type, number of rooms and 
       the guest's name. Returns true if successful otherwise false and a 
       vector of the guest's reservations. */
       Map<Character, Integer> cancelRoom(char type, int number, 
         String name) throws Exception;
}