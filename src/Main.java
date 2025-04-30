import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player = null;
        boolean gameRunning = true;
        Room roomManager = new Room();

        System.out.println("                  LABRYNTH OF THE LOST                      ");
        System.out.println();
        System.out.println("Welcome, brave adventurer!");
        System.out.println("You stand at the entrance of the ancient Labrynth of the Lost.");
        System.out.println("Many have entered, but few have returned to tell their tales.");
        System.out.println();
        System.out.println("Before you begin your perilous journey, you must choose your character.");
        System.out.println();
        System.out.println("Available Characters:");
        System.out.println("1. Warrior - A mighty fighter skilled in combat");
        System.out.println("2. Mage - A powerful spellcaster with arcane knowledge");
        System.out.println("3. Rogue - A nimble thief with quick reflexes");
        System.out.println();

        while (player == null) {
            System.out.print("Enter your choice (1-3): ");
            String choice = scanner.nextLine();
            
            System.out.print("Enter your character's name: ");
            String name = scanner.nextLine();

            switch(choice) {
                case "1":
                    player = new Player(name, "warrior");
                    break;
                case "2":
                    player = new Player(name, "mage");
                    break;
                case "3":
                    player = new Player(name, "rogue");
                    break;
                default:
                    System.out.println("Invalid choice! Please select 1, 2, or 3.");
            }
        }

        System.out.println("\nCharacter Created!");
        System.out.println("Name: " + player.getName());
        System.out.println("Class: " + player.getCharacterClass());
        System.out.println("Health: " + player.getHealth() + "/" + player.getMaxHealth());
        System.out.println("Damage: " + player.getDamage());
        System.out.println("Gold: " + player.getGold());
        System.out.println("\nPress Enter to begin your journey...");
        scanner.nextLine();

        while (gameRunning && player.isAlive()) {
            System.out.println("\n=== " + player.getName() + " the " + player.getCharacterClass() + " ===");
            System.out.println("Health: " + player.getHealth() + "/" + player.getMaxHealth());
            System.out.println("Gold: " + player.getGold());
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Explore forward");
            System.out.println("2. Quit game");
            
            String action = scanner.nextLine();
            
            switch(action) {
                case "1":
                    System.out.println("You venture deeper into the labyrinth...");
                    String nextRoom = roomManager.getNextRoom();
                    Room currentRoom = createRoom(nextRoom);
                    currentRoom.playRoom(player);
                    roomManager.setCurrentRoom(nextRoom);
                    
                    // Check if we've reached the final boss
                    if (nextRoom.equals("finalboss")) {
                        FinalBossRoom finalBoss = (FinalBossRoom) currentRoom;
                        if (finalBoss.isGameWon()) {
                            gameRunning = false;
                        }
                    }
                    break;
                case "2":
                    gameRunning = false;
                    System.out.println("Thanks for playing!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select 1 or 2.");
            }
        }

        if (!player.isAlive()) {
            System.out.println("\nGame Over! " + player.getName() + " has fallen in the labyrinth.");
        }

        scanner.close();
    }

    private static Room createRoom(String roomType) {
        switch(roomType) {
            case "decision":
                return new DecisionRoom();
            case "horde":
                return new HordeRoom();
            case "puzzle":
                return new PuzzleRoom();
            case "miniboss":
                return new MinibossRoom();
            case "chasm":
                return new ChasmRoom();
            case "trader":
                return new TraderRoom();
            case "finalboss":
                return new FinalBossRoom();
            default:
                return new Room();
        }
    }
}
