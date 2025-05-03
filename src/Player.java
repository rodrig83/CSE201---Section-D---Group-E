import java.util.ArrayList;

/**
 * The Player class represents a playable character in the game.
 * Each player has attributes such as health, damage, gold, inventory,
 * and a chosen character class (warrior, mage, or rogue).
 */
public class Player {
    private String name;
    private String characterClass;
    private int health;
    private int maxHealth;
    private int damage;
    private int gold;
    private ArrayList<Item> inventory;

    /**
     * Constructs a Player with the given name and character class.
     * Initializes health, damage, and gold based on the class.
     * @param name The name of the player
     * @param characterClass The class of the player ("warrior", "mage", "rogue")
     */
    public Player(String name, String characterClass) {
        this.name = name;
        this.characterClass = characterClass;
        this.inventory = new ArrayList<>();
        
        switch(characterClass.toLowerCase()) {
            case "warrior":
                this.maxHealth = 100;
                this.health = maxHealth;
                this.damage = 15;
                this.gold = 50;
                break;
            case "mage":
                this.maxHealth = 70;
                this.health = maxHealth;
                this.damage = 20;
                this.gold = 30;
                break;
            case "rogue":
                this.maxHealth = 80;
                this.health = maxHealth;
                this.damage = 12;
                this.gold = 100;
                break;
        }
    }

    /** @return The player's name */
    public String getName() { return name; }

    /** @return The player's character class */
    public String getCharacterClass() { return characterClass; }

    /** @return The player's current health */
    public int getHealth() { return health; }

    /** @return The player's max health */
    public int getMaxHealth() { return maxHealth; }

    /** @return The player's damage value */
    public int getDamage() { return damage; }

    /** @return The player's gold amount */
    public int getGold() { return gold; }

    /**
     * Sets the player's current health, capped at max health.
     * @param health New health value
     */
    public void setHealth(int health) { 
        this.health = Math.min(health, maxHealth); 
    }

    /**
     * Sets the player's damage value.
     * @param damage New damage value
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Adds gold to the player's total.
     * @param amount The amount of gold to add
     */
    public void addGold(int amount) {
        this.gold += amount;
    }

    /** @return true if player is alive (health > 0), false otherwise */
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * Reduces the player's health by a specified amount.
     * @param damage The amount of damage taken
     */
    public void takeDamage(int damage) {
        this.health = Math.max(0, this.health - damage);
    }

    /**
     * Heals the player by a specified amount, up to max health.
     * @param amount The amount to heal
     */
    public void heal(int amount) {
        this.health = Math.min(maxHealth, this.health + amount);
    }

    /**
     * Increases the player's damage stat.
     * @param amount Amount of damage to add
     */
    public void increaseDamage(int amount) {
        this.damage += amount;
    }

    /**
     * Adds an item to the player's inventory.
     * @param item The item to add
     */
    public void addItem(Item item) {
        inventory.add(item);
    }

    /**
     * Removes an item from the player's inventory.
     * @param item The item to remove
     */
    public void removeItem(Item item) {
        inventory.remove(item);
    }

    /** @return A copy of the player's inventory */
    public ArrayList<Item> getInventory() {
        return new ArrayList<>(inventory);
    }

    /**
     * Sets a new maximum health value for the player.
     * @param maxHealth The new maximum health
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * Displays the player's inventory in the console.
     */
    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Your inventory:");
            for (int i = 0; i < inventory.size(); i++) {
                Item item = inventory.get(i);
                System.out.println((i + 1) + ". " + item.getName() + " - " + item.getDescription());
            }
        }
    }
}
