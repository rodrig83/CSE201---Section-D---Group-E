import java.util.Random;
import java.util.Scanner;

public class FinalBossRoom extends Room {
    private Random random;
    private boolean gameWon = false;

    public FinalBossRoom() {
        super();
        random = new Random();
    }

    public void playRoom(Player player) {
        System.out.println("\nAs you descend deeper into the labyrinth, a thick fog begins to surround you...");
        System.out.println("The air grows heavy with an ominous presence...");
        System.out.println("Suddenly, you hear heavy footsteps approaching through the mist...");
        System.out.println("A massive Minotaur emerges from the fog, its eyes burning with fury!");
        System.out.println("This is the final battle - there's no turning back now!");
        
        Enemy finalBoss = new Enemy("minotaur");
        // Make the final boss stronger
        finalBoss.takeDamage(-50); // Add 50 health to make it tougher
        
        boolean usedSpecialAbility = false;
        int specialAbilityThreshold = finalBoss.health() / 2;
        int turnsUntilFogAttack = 3; // Countdown for fog attack

        while (player.isAlive() && finalBoss.health() > 0) {
            // Player's turn
            System.out.println("\nWhat will you do?");
            System.out.println("1. Attack");
            System.out.println("2. Use Item (if available)");
            System.out.print("Choose your action (1-2): ");
            
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            
            if (choice == 1) {
                System.out.println("You strike the Minotaur for " + player.getDamage() + " damage!");
                finalBoss.takeDamage(player.getDamage());
            } else if (choice == 2) {
                System.out.println("You don't have any items to use!");
                continue;
            } else {
                System.out.println("Invalid choice! You hesitate and lose your turn!");
            }

            if (finalBoss.health() <= 0) {
                System.out.println("You have defeated the mighty Minotaur!");
                gameWon = true;
                break;
            }

            // Boss's turn
            if (!usedSpecialAbility && finalBoss.health() <= specialAbilityThreshold) {
                System.out.println("The Minotaur enters a berserk rage! Its attacks become more powerful!");
                usedSpecialAbility = true;
            }

            turnsUntilFogAttack--;
            if (turnsUntilFogAttack <= 0) {
                System.out.println("The fog thickens around you, making it harder to breathe!");
                int fogDamage = 5;
                player.takeDamage(fogDamage);
                System.out.println("You take " + fogDamage + " damage from the toxic fog!");
                turnsUntilFogAttack = 3;
            }

            int bossDamage = finalBoss.attack();
            if (usedSpecialAbility) {
                bossDamage *= 2;
                System.out.println("The Minotaur's rage-enhanced attack deals " + bossDamage + " damage!");
            } else {
                System.out.println("The Minotaur attacks for " + bossDamage + " damage!");
            }
            
            player.takeDamage(bossDamage);
            
            if (!player.isAlive()) {
                System.out.println("The Minotaur has defeated you...");
                break;
            }

            System.out.println("\nYour health: " + player.getHealth());
            System.out.println("Minotaur's health: " + finalBoss.health());
            System.out.println("Turns until next fog attack: " + turnsUntilFogAttack);
        }

        if (gameWon) {
            System.out.println("\nVICTORY! You have defeated the final boss!");
            System.out.println("As the Minotaur falls, the fog begins to clear...");
            System.out.println("You notice a staircase descending deeper into the labyrinth...");
            System.out.println("You cautiously make your way down...");
            System.out.println("\nTo Be Continued...");
            System.out.println("\nThank you for playing Labyrinth of the Lost!");
        } else {
            System.out.println("\nGAME OVER");
            System.out.println("The Minotaur has claimed another victim...");
            System.out.println("Your journey ends here...");
        }
    }

    public boolean isGameWon() {
        return gameWon;
    }
} 