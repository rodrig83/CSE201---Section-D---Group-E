
public class Enemy {
	private String type;
	public int health;
	private int damage;
	
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
	
	public Enemy() {
		this.type = "bat";
		this.damage = 1;
		this.health = 1;
	}
	
	public int attack() {
		return damage;
	}

	public int health() {
		return health;
	}
	
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
