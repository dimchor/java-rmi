package main.java;

import java.rmi.Naming;
import java.util.Map;

/**
 * HRClient
 */
public class HRClient {

    public static void printUsage() {
        System.out.println("usage: list <hostname>"+
            "|book <hostname> <type> <number> <name>"+
            "|guests <hostname>"+
            "|cancel <hostname> <type> <number> <name>");
    }

    public static void print(Map<Character, Integer> map) {
        if (map == null) {
            System.out.println("empty");
            return;
        }
        for (var entry: map.entrySet())
            System.out.println("Room type: " + entry.getKey() + ": " + 
                entry.getValue());
    }

    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                printUsage();
                return;
            }

            HRInterface hr = (HRInterface) 
                Naming.lookup("rmi://" + args[1] + ":1099/HR");


            switch (args[0]) {
                case "list":
                    print(hr.listRooms());
                    break;
                case "book": {
                    if (args.length < 5) {
                        printUsage();
                        return;
                    }

                    var pair = hr.bookRoom(args[2].charAt(0), 
                        Integer.parseInt(args[3]), args[4]);
                    System.out.println("booked rooms: " + pair.FIRST + 
                        ", total cost: " + pair.SECOND);
                    break;
                }
                case "guests": {
                    if (args.length < 2) {
                        printUsage();
                        return;
                    }

                    var map = hr.listGuests();
                    if (map.isEmpty()) {
                        System.out.println("empty");
                        return;
                    }
                    for (var entry: map.entrySet()) {
                        System.out.println("guest:" + entry.getKey());
                        int totalCost = 0;
                        for (var room: entry.getValue().entrySet()) {
                            final int COST = room.getValue() * 
                                hr.getRoomCost(room.getKey());
                            System.out.println("\troom type: " + room.getKey() +
                                 ", n: " + room.getValue() + ", cost: " + COST);
                            totalCost += COST;
                        }
                        System.out.println("total cost: " + totalCost);
                    }
                    break;
                }
                case "cancel": {
                    if (args.length < 5) {
                        printUsage();
                        return;
                    }

                    var map = hr.cancelRoom(args[2].charAt(0), 
                        Integer.parseInt(args[3]), args[4]);
                    print(map);
                    break;
                }
                default:
                    System.out.println("invalid argument");
                    printUsage();
                    break;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}