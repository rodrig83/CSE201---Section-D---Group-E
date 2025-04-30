import java.util.Scanner;

public class TraderRoom extends Room {
    private Item healthPotion;
    private Item weaponUpgrade;
    private Item firePotion;
    private Scanner scanner;

    public TraderRoom() {
        super();
        healthPotion = new Item("Health Potion", 15, "Restores 20 health");
        weaponUpgrade = new Item("Weapon Upgrade", 25, "Increases damage by 5");
        firePotion = new Item("Fire Potion", 20, "Deals 15 damage to all enemies in the next room");
        scanner = new Scanner(System.in);
    }

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
