import java.util.ArrayList;
import java.util.Random;

public class Room {
    private ArrayList<String> availableRooms;
    private Random random;
    private String currentRoom;

    public Room() {
        availableRooms = new ArrayList<>();
        random = new Random();
        currentRoom = "start";
        initializeRooms();
    }

    private void initializeRooms() {
        // Rooms will be added here later in second iteration or beyond or like this week,
        availableRooms.add("start");
    }

    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String room) {
        this.currentRoom = room;
    }

    public String getRandomRoom() {
        if (availableRooms.size() <= 1) {
            return "start";
        }
        int index;
        String newRoom;
        do {
            index = random.nextInt(availableRooms.size());
            newRoom = availableRooms.get(index);
        } while (newRoom.equals(currentRoom));
        
        return newRoom;
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
}
