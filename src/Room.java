import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

/**
 * The Room class manages the flow of rooms in the game and handles the randomization
 * and sequencing of different room types, such as puzzle rooms, trader rooms, and bosses.
 */
public class Room {
    private ArrayList<String> availableRooms;
    private ArrayList<String> remainingRooms;
    private Random random;
    private String currentRoom;
    private int roomCount = 0;

    /**
     * Constructs a Room object and initializes the room list and order.
     */
    public Room() {
        availableRooms = new ArrayList<>();
        remainingRooms = new ArrayList<>();
        random = new Random();
        currentRoom = "start";
        initializeRooms();
    }

    /**
     * Initializes the available room types and sets up random room order.
     */
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

    /**
     * Gets the name of the current room.
     * @return The current room name
     */
    public String getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Sets the name of the current room and increments room counter.
     * @param room The room name to set as current
     */
    public void setCurrentRoom(String room) {
        this.currentRoom = room;
        roomCount++;
    }

    /**
     * Determines and returns the next room based on progression logic.
     * @return The name of the next room
     */
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

    /**
     * Adds a room to the list of available rooms.
     * @param roomName The name of the room to add
     */
    public void addRoom(String roomName) {
        if (!availableRooms.contains(roomName)) {
            availableRooms.add(roomName);
        }
    }

    /**
     * Removes a room from the list of available rooms.
     * @param roomName The name of the room to remove
     */
    public void removeRoom(String roomName) {
        availableRooms.remove(roomName);
    }

    /**
     * Returns a copy of the list of all available room names.
     * @return A list of available room names
     */
    public ArrayList<String> getAvailableRooms() {
        return new ArrayList<>(availableRooms);
    }

    /**
     * Checks if a given room name is valid and available.
     * @param roomName The room name to check
     * @return true if the room is valid, false otherwise
     */
    public boolean isValidRoom(String roomName) {
        return availableRooms.contains(roomName);
    }

    /**
     * The base behavior when entering a generic room. Can be overridden by subclasses.
     * @param player The player entering the room
     */
    public void playRoom(Player player) {
        // Base room implementation - can be overridden by specific room types
        System.out.println("You enter a room...");
    }
}
