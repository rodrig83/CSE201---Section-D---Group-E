import java.util.Scanner;

public class DecisionRoom extends Room {
    private Item potion;
    private Item sword;
    private Scanner scanner;

    public DecisionRoom() {
        super();
        this.potion = new Item("Health Potion", 0, "Restores 20 health");
        this.sword = new Item("Weapon Upgrade", 0, "Increases damage by 5");
        this.scanner = new Scanner(System.in);
    }

    public void playRoom(Player player) {
        System.out.println("\nYou enter a room with two pedestals, each holding a different item.");
        System.out.println("On the left pedestal: A " + potion.getName() + " (" + potion.getDescription() + ")");
        System.out.println("On the right pedestal: A " + sword.getName() + " (" + sword.getDescription() + ")");
        System.out.println("A mysterious voice echoes: 'Choose wisely, for taking both will have consequences...'");
        
        if (player.getCharacterClass().equals("rogue")) {
            System.out.println("As an assassin, you notice you could potentially grab both items quickly...");
        }
        
        System.out.println("\nWhat will you do?");
        System.out.println("1. Take the " + potion.getName());
        System.out.println("2. Take the " + sword.getName());
        System.out.println("3. Try to take both items");
        System.out.print("Enter your choice (1-3): ");
        
        String choice = scanner.nextLine();
        
        switch(choice) {
            case "1":
                System.out.println("You take the " + potion.getName() + ".");
                System.out.println("The other pedestal disappears into the floor!");
                player.heal(20);
                System.out.println("You restore 20 health!");
                System.out.println("Your health: " + player.getHealth());
                break;
                
            case "2":
                System.out.println("You take the " + sword.getName() + ".");
                System.out.println("The other pedestal disappears into the floor!");
                player.increaseDamage(5);
                System.out.println("Your damage has increased by 5!");
                System.out.println("Your damage: " + player.getDamage());
                break;
                
            case "3":
                if (player.getCharacterClass().equals("rogue")) {
                    System.out.println("Using your assassin's quick reflexes, you grab both items in a flash!");
                    System.out.println("The pedestals disappear, but you've already secured both items!");
                    player.heal(20);
                    player.increaseDamage(5);
                    System.out.println("You restore 20 health and increase your damage by 5!");
                    System.out.println("Your health: " + player.getHealth());
                    System.out.println("Your damage: " + player.getDamage());
                } else {
                    System.out.println("As you reach for the second item, a magical force strikes you!");
                    int damage = 15;
                    player.takeDamage(damage);
                    System.out.println("You take " + damage + " damage!");
                    System.out.println("Both pedestals disappear into the floor!");
                    System.out.println("Your health: " + player.getHealth());
                }
                break;
                
            default:
                System.out.println("Invalid choice! The pedestals disappear into the floor!");
        }
        
        System.out.println("You leave the decision room.");
    }
} 