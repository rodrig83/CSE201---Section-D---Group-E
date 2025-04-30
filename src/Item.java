import java.util.Random;
import java.util.ArrayList;

public class Item {
	private String name;
	private int cost;
	private String description;
	private Random r = new Random();
	
	public Item() {}
	
	public Item(String name, int cost, String description) {
		this.name = name;
		this.cost = cost;
		this.description = description;
	}
	
	public void doItem(Player player, Item item, ArrayList<Enemy> enemies) {
		if (item.getName() == "Health Potion") {
			player.heal(20);
		}
		else if (item.getName() == "Weapon Upgrade") {
			player.increaseDamage(5);
		}
		else if (item.getName() == "Fire Potion") {
			// Fire potion effect is handled in the room where it's used
		}
		else {}
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	
	
	
}
