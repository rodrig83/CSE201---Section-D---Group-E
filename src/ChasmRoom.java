import java.util.Random;
import java.util.Scanner;

/**
 * The ChasmRoom class represents a challenge room in the labyrinth where the player must attempt
 * to jump across a deep chasm. Success depends on the player's class or a probability check.
 * Warriors always succeed, while other classes have a 75% success rate.
 *
 * Failure results in death, ending the game. Success rewards the player with gold.
 */
public class ChasmRoom extends Room {
    private Random random;

    /**
     * Constructs a ChasmRoom and initializes the random number generator used
     * for determining jump success for non-warrior classes.
     */
    public ChasmRoom() {
        super();
        random = new Random();
    }

    /**
     * Handles the gameplay logic of the ChasmRoom.
     * Prompts the player for input to attempt the jump, calculates the success
     * or failure based on class or probability, and applies the outcome accordingly.
     * If the player refuses to jump twice, they are considered trapped and their health is set to 0.
     *
     * @param player The Player object attempting to navigate the ChasmRoom
     */
    public void playRoom(Player player) {
        System.out.println("\nYou enter a large room with a deep chasm running through its center.");
        System.out.println("The chasm is too wide to walk around, and the walls are too smooth to climb.");
        System.out.println("Your only option is to jump across...");
        System.out.println("Do you dare to make the leap? (y/n)");
        
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().toLowerCase();
        
        if (!choice.equals("y")) {
            System.out.println("You decide to turn back, but the door behind you has mysteriously closed...");
            System.out.println("You are trapped in the room with the chasm...");
            System.out.println("Do you want to try jumping now? (y/n)");
            choice = scanner.nextLine().toLowerCase();
            if (!choice.equals("y")) {
                System.out.println("You remain trapped in the room...");
                player.setHealth(0);
                return;
            }
        }

        System.out.println("\nYou take a deep breath and prepare to jump...");
        
        boolean success = false;
        if (player.getCharacterClass().equals("warrior")) {
            // Warrior always succeeds
            success = true;
            System.out.println("Your warrior's strength and agility allow you to easily clear the chasm!");
        } else {
            // 75% chance for other classes
            success = random.nextDouble() < 0.75;
            if (success) {
                System.out.println("You make a daring leap and just barely make it to the other side!");
            } else {
                System.out.println("Your jump falls short...");
                System.out.println("You plummet into the darkness below...");
                player.setHealth(0);
                return;
            }
        }

        if (success) {
            System.out.println("You successfully cross the chasm!");
            int goldReward = 25;
            player.addGold(goldReward);
            System.out.println("You find " + goldReward + " gold on the other side!");
            System.out.println("Your total gold: " + player.getGold());
        }
        
        System.out.println("You leave the chasm room.");
    }
} 
