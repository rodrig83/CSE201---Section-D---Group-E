public class Player {
    private String name;
    private String characterClass;
    private int health;
    private int maxHealth;
    private int damage;
    private int gold;

    public Player(String name, String characterClass) {
        this.name = name;
        this.characterClass = characterClass;
        
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

    // Getters and setters
    public String getName() { return name; }
    public String getCharacterClass() { return characterClass; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getDamage() { return damage; }
    public int getGold() { return gold; }

    public void setHealth(int health) { 
        this.health = Math.min(health, maxHealth); 
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
}
