import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class PuzzleRoom extends Room {
    private int[] correctSequence;
    private Random random;
    private static final int SEQUENCE_LENGTH = 4;
    private static final int MAX_TILE_NUMBER = 5;

    public PuzzleRoom() {
        super();
        random = new Random();
        generateSequence();
    }

    private void generateSequence() {
        correctSequence = new int[SEQUENCE_LENGTH];
        for (int i = 0; i < SEQUENCE_LENGTH; i++) {
            correctSequence[i] = random.nextInt(MAX_TILE_NUMBER) + 1;
        }
    }

    public void playRoom(Player player) {
        System.out.println("\nYou enter a mysterious room with " + MAX_TILE_NUMBER + " numbered tiles on the floor.");
        System.out.println("As you step in, the tiles briefly light up in a sequence...");
        
        // Show the sequence briefly
        System.out.print("Sequence: ");
        for (int num : correctSequence) {
            System.out.print(num + " ");
            try {
                Thread.sleep(1000); // Pause for 1 second between each number
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\nThe sequence fades away...");
        
        // Clear the console (simulated)
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
        
        System.out.println("Now you must step on the tiles in the correct sequence!");
        System.out.println("Enter the numbers (1-" + MAX_TILE_NUMBER + ") separated by spaces:");
        System.out.println("You only have one chance to get it right!");
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Your sequence: ");
        String input = scanner.nextLine();
        String[] inputNumbers = input.split(" ");
        
        if (inputNumbers.length != SEQUENCE_LENGTH) {
            System.out.println("Wrong sequence! You needed to enter exactly " + SEQUENCE_LENGTH + " numbers!");
            int damage = 15;
            player.takeDamage(damage);
            System.out.println("You take " + damage + " damage from frustration!");
            System.out.println("Your health: " + player.getHealth());
            System.out.println("You leave the puzzle room.");
            return;
        }
        
        int[] playerSequence = new int[SEQUENCE_LENGTH];
        boolean validInput = true;
        
        try {
            for (int i = 0; i < SEQUENCE_LENGTH; i++) {
                playerSequence[i] = Integer.parseInt(inputNumbers[i]);
                if (playerSequence[i] < 1 || playerSequence[i] > MAX_TILE_NUMBER) {
                    System.out.println("Wrong sequence! Numbers must be between 1 and " + MAX_TILE_NUMBER + "!");
                    validInput = false;
                    break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong sequence! You needed to enter valid numbers!");
            validInput = false;
        }
        
        if (!validInput) {
            int damage = 15;
            player.takeDamage(damage);
            System.out.println("You take " + damage + " damage from frustration!");
            System.out.println("Your health: " + player.getHealth());
            System.out.println("You leave the puzzle room.");
            return;
        }
        
        if (Arrays.equals(playerSequence, correctSequence)) {
            System.out.println("Correct! The exit door opens!");
            int goldReward = 25;
            player.addGold(goldReward);
            System.out.println("You found " + goldReward + " gold in the room!");
            System.out.println("Your total gold: " + player.getGold());
        } else {
            System.out.println("Wrong sequence! The room remains locked.");
            int damage = 15;
            player.takeDamage(damage);
            System.out.println("You take " + damage + " damage from frustration!");
            System.out.println("Your health: " + player.getHealth());
        }
        
        System.out.println("You leave the puzzle room.");
    }
} 