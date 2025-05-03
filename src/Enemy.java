/**
 * The Enemy class represents an enemy in the game with specific health and damage values
 * based on its type (e.g., ork, goblin, minotaur). It includes methods for attacking and
 * taking damage.
 */
public class Enemy {
	private String type;
	public int health;
	private int damage;
	
	/**
	* Constructs an Enemy with a specific type and initializes health and damage accordingly.
	* @param kind The type of the enemy (e.g., "ork", "goblin", "minotaur")
	*/
	public Enemy(String kind) {
		this.type = kind;
		
		if (type.equals("ork")) {
			this.health = 20;
			this.damage = 4;
		}
		else if (type.equals("goblin")) {
			this.health = 10;
			this.damage = 2;
		}
		else if (type.equals("minotaur")) {
			this.health = 100;
			this.damage = 10;
		}
		else if (type.equals("golum")) {
			this.health = 35;
			this.damage = 5;
		}
		else {
			this.health = 1;
			this.damage = 1;
		}
	}

	/**
	* Constructs a default Enemy of type "bat" with minimal stats.
	*/
	public Enemy() {
		this.type = "bat";
		this.damage = 1;
		this.health = 1;
	}

	/**
     	* Returns the damage the enemy can deal.
     	* @return The enemy's damage value
     	*/
	public int attack() {
		return damage;
	}

	/**
     	* Returns the current health of the enemy.
     	* @return The enemy's current health
     	*/
	public int health() {
		return health;
	}
 
	/**
     	* Reduces the enemy's health by a specified amount and displays status messages.
     	* @param num The amount of damage taken
     	*/
	public void takeDamage(int num) {
		this.health -= num;
		
		if (this.health <= 0) {
			if (this.type == "minotaur") {
				System.out.println("Boss Defeated");
			}
			else {
				System.out.println("Enemy Slain");
			}
		}
		else {
			System.out.println("The ememy has " + this.health + " hp left.");
		}
	}
	
}
