import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Room {
    private ArrayList<String> availableRooms;
    private ArrayList<String> remainingRooms;
    private Random random;
    private String currentRoom;
    private int roomCount = 0;

    public Room() {
        availableRooms = new ArrayList<>();
        remainingRooms = new ArrayList<>();
        random = new Random();
        currentRoom = "start";
        initializeRooms();
    }

    private void initializeRooms() {
        // Add all available rooms
        availableRooms.add("decision");    // Always first
        availableRooms.add("horde");       // Random rooms
        availableRooms.add("puzzle");
        availableRooms.add("miniboss");
        availableRooms.add("chasm");
        availableRooms.add("trader");      // Always before final boss
        availableRooms.add("finalboss");   // Always last
        
        // Initialize remaining rooms (excluding decision and finalboss)
        remainingRooms.addAll(availableRooms);
        remainingRooms.remove("decision");
        remainingRooms.remove("finalboss");
        remainingRooms.remove("trader");  // Remove trader from random pool
        Collections.shuffle(remainingRooms);
    }

    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String room) {
        this.currentRoom = room;
        roomCount++;
    }

    public String getNextRoom() {
        // First room is always DecisionRoom
        if (roomCount == 0) {
            return "decision";
        }
        
        // Last room is always FinalBossRoom
        if (roomCount >= availableRooms.size() - 1) {
            return "finalboss";
        }
        
        // Second to last room is always TraderRoom
        if (roomCount == availableRooms.size() - 2) {
            return "trader";
        }
        
        // Get next room from remaining rooms
        if (!remainingRooms.isEmpty()) {
            return remainingRooms.remove(0);
        }
        
        // Fallback (should never happen)
        return "trader";
    }

    public void addRoom(String roomName) {
        if (!availableRooms.contains(roomName)) {
            availableRooms.add(roomName);
        }
    }

    public void removeRoom(String roomName) {
        availableRooms.remove(roomName);
    }

    public ArrayList<String> getAvailableRooms() {
        return new ArrayList<>(availableRooms);
    }

    public boolean isValidRoom(String roomName) {
        return availableRooms.contains(roomName);
    }

    public void playRoom(Player player) {
        // Base room implementation - can be overridden by specific room types
        System.out.println("You enter a room...");
    }
}
