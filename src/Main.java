import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        Player player = null;
        boolean gameRunning = true;

        System.out.println("                 LABRYNTH OF THE LOST                      ");
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
           

            // This is wher the random room selecter is and what rooms are available and will come next. 
            // Just the base level 

            System.out.println("2. Quit game");
            
            String action = scanner.nextLine();
            
            switch(action) {
                case "1":
                    System.out.println("You venture deeper into the labyrinth...");

                    //now current room is updated and where you room is at. 
                    break;
                case "2":
                    gameRunning = false;
                    System.out.println("Thanks for playing!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select 1-x.");
            }
        }

        if (!player.isAlive()) {
            System.out.println("\nGame Over! " + player.getName() + " has fallen in the labyrinth.");
        }

        scanner.close();
    }
}
