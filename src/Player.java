import java.util.ArrayList;

public class Player {
    private String name;
    private String characterClass;
    private int health;
    private int maxHealth;
    private int damage;
    private int gold;
    private ArrayList<Item> inventory;

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

    public String getName() { return name; }
    public String getCharacterClass() { return characterClass; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getDamage() { return damage; }
    public int getGold() { return gold; }

    public void setHealth(int health) { 
        this.health = Math.min(health, maxHealth); 
    }
    
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void addGold(int amount) {
        this.gold += amount;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        this.health = Math.max(0, this.health - damage);
    }

    public void heal(int amount) {
        this.health = Math.min(maxHealth, this.health + amount);
    }

    public void increaseDamage(int amount) {
        this.damage += amount;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public ArrayList<Item> getInventory() {
        return new ArrayList<>(inventory);
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

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