package main.java;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.*;


/**
 * HRServer
 */
public class HRServer {
    public HRServer() {
        try {
            HRInterface c = new HRImplementation();
          Naming.rebind("rmi://localhost:1099/HR", c);
        } catch (Exception e) {
          System.out.println("Trouble: " + e);
        }
      }
    public static void main(String[] args) {
        System.out.println("RMI server started");
        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(1099);
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }
        new HRServer();
    }
}