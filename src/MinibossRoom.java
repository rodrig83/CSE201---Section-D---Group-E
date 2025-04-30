import java.util.Random;
import java.util.Scanner;

public class MinibossRoom extends Room {
    private Random random;
    private Item specialReward;
    private Scanner scanner;

    public MinibossRoom() {
        super();
        random = new Random();
        specialReward = new Item("Miniboss Reward", 0, "Increases max health by 20");
        scanner = new Scanner(System.in);
    }

    public void playRoom(Player player) {
        System.out.println("\nYou enter a grand chamber with ominous energy in the air...");
        System.out.println("A powerful Minotaur stands before you, its massive form blocking the exit!");
        Enemy minotaur = new Enemy("minotaur");
        System.out.println("The Minotaur lets out a thunderous roar and prepares to attack!");

        boolean usedSpecialAbility = false;
        int specialAbilityThreshold = minotaur.health() / 2; // When minotaur is at half health

        while (player.isAlive() && minotaur.health() > 0) {
            // Player's turn
            System.out.println("\nWhat will you do?");
            System.out.println("1. Attack");
            System.out.println("2. Use Item");
            System.out.print("Choose your action (1-2): ");
            
            String choice = scanner.nextLine();
            
            if (choice.equals("1")) {
                System.out.println("You strike the Minotaur for " + player.getDamage() + " damage!");
                minotaur.takeDamage(player.getDamage());
            } else if (choice.equals("2")) {
                if (player.getInventory().isEmpty()) {
                    System.out.println("You don't have any items to use!");
                    continue;
                }
                
                System.out.println("\nYour inventory:");
                player.displayInventory();
                System.out.print("Choose an item to use (1-" + player.getInventory().size() + "): ");
                
                try {
                    int itemChoice = Integer.parseInt(scanner.nextLine()) - 1;
                    if (itemChoice >= 0 && itemChoice < player.getInventory().size()) {
                        Item selectedItem = player.getInventory().get(itemChoice);
                        if (selectedItem.getName().equals("Fire Potion")) {
                            minotaur.takeDamage(15);
                            System.out.println("You throw the Fire Potion at the Minotaur, dealing 15 damage!");
                            player.removeItem(selectedItem);
                        } else if (selectedItem.getName().equals("Health Potion")) {
                            player.heal(20);
                            System.out.println("You drink the Health Potion and restore 20 health!");
                            System.out.println("Your health: " + player.getHealth());
                            player.removeItem(selectedItem);
                        } else {
                            System.out.println("You can't use that item in combat!");
                        }
                    } else {
                        System.out.println("Invalid item choice!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number!");
                }
            } else {
                System.out.println("Invalid choice! You hesitate and lose your turn!");
            }

            if (minotaur.health() <= 0) {
                System.out.println("You have defeated the mighty Minotaur!");
                break;
            }

            if (!usedSpecialAbility && minotaur.health() <= specialAbilityThreshold) {
                System.out.println("The Minotaur enters a rage! Its next attack will be devastating!");
                usedSpecialAbility = true;
            }

            int minotaurDamage = minotaur.attack();
            if (usedSpecialAbility) {
                minotaurDamage *= 2; // Double damage when enraged
                System.out.println("The Minotaur's rage-enhanced attack deals " + minotaurDamage + " damage!");
            } else {
                System.out.println("The Minotaur attacks for " + minotaurDamage + " damage!");
            }
            
            player.takeDamage(minotaurDamage);
            
            if (!player.isAlive()) {
                System.out.println("The Minotaur has defeated you...");
                break;
            }

            // Display status
            System.out.println("\nYour health: " + player.getHealth());
            System.out.println("Minotaur's health: " + minotaur.health());
        }

        if (player.isAlive()) {
            System.out.println("\nVictory! You have defeated the Minotaur!");
            int goldReward = 50;
            player.addGold(goldReward);
            System.out.println("You found " + goldReward + " gold in the chamber!");
            
            // Special reward
            System.out.println("You also find a special reward: " + specialReward.getName());
            System.out.println("This item will be very useful in your journey!");
            player.setMaxHealth(player.getMaxHealth() + 20);
            player.heal(20);
            System.out.println("Your max health has increased by 20!");
            System.out.println("Your health: " + player.getHealth() + "/" + player.getMaxHealth());
            
            System.out.println("Your total gold: " + player.getGold());
        }

        System.out.println("You leave the miniboss chamber.");
    }
} 