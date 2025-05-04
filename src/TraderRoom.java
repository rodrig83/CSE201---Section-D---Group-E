import java.util.Scanner;

/**
 * The TraderRoom class represents a room where the player can purchase useful items
 * using gold. Items include health potions, weapon upgrades, and fire potions.
 */
public class TraderRoom extends Room {
    private Item healthPotion;
    private Item weaponUpgrade;
    private Item firePotion;
    private Scanner scanner;

    /**
     * Constructs a TraderRoom and initializes the three predefined items (Health Potion,
     * Weapon Upgrade, and Fire Potion) along with a Scanner for reading player input.
     */
    public TraderRoom() {
        super();
        healthPotion = new Item("Health Potion", 15, "Restores 20 health");
        weaponUpgrade = new Item("Weapon Upgrade", 25, "Increases damage by 5");
        firePotion = new Item("Fire Potion", 20, "Deals 15 damage to all enemies in the next room");
        scanner = new Scanner(System.in);
    }

    /**
     * Executes the shop interaction logic when the player enters the TraderRoom.
     * Displays available items, handles gold deduction, applies item effects, and
     * manages user input including invalid choices. Each item choice corresponds to a
     * different stat modification or inventory action.
     *
     * @param player The player currently in the room, whose gold, health, damage, or inventory may change
     */
    public void playRoom(Player player) {
        System.out.println("\nYou enter a dimly lit room. A hooded figure stands behind a wooden counter.");
        System.out.println("The trader greets you with a sly smile. 'Welcome, adventurer. Care to browse my wares?'");
        
        boolean shopping = true;
        while (shopping) {
            System.out.println("\nYour gold: " + player.getGold());
            System.out.println("Available items:");
            System.out.println("1. " + healthPotion.getName() + " - " + healthPotion.getCost() + " gold - " + healthPotion.getDescription());
            System.out.println("2. " + weaponUpgrade.getName() + " - " + weaponUpgrade.getCost() + " gold - " + weaponUpgrade.getDescription());
            System.out.println("3. " + firePotion.getName() + " - " + firePotion.getCost() + " gold - " + firePotion.getDescription());
            System.out.println("4. Leave the shop");
            
            System.out.print("Choose an item (1-4): ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    if (player.getGold() >= healthPotion.getCost()) {
                        player.addGold(-healthPotion.getCost());
                        player.heal(20);
                        System.out.println("You drink the health potion and restore 20 health!");
                        System.out.println("Your health: " + player.getHealth());
                    } else {
                        System.out.println("Not enough gold!");
                    }
                    break;
                case "2":
                    if (player.getGold() >= weaponUpgrade.getCost()) {
                        player.addGold(-weaponUpgrade.getCost());
                        player.increaseDamage(5);
                        System.out.println("Your weapon has been upgraded! Damage increased by 5!");
                        System.out.println("Your damage: " + player.getDamage());
                    } else {
                        System.out.println("Not enough gold!");
                    }
                    break;
                case "3":
                    if (player.getGold() >= firePotion.getCost()) {
                        player.addGold(-firePotion.getCost());
                        player.addItem(firePotion);
                        System.out.println("You've purchased a Fire Potion! It will be used in the next room.");
                    } else {
                        System.out.println("Not enough gold!");
                    }
                    break;
                case "4":
                    shopping = false;
                    System.out.println("The trader waves as you leave. 'Come back soon!'");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
