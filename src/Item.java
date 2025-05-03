import java.util.Random;
import java.util.ArrayList;

/**
 * The Item class represents usable or purchasable objects within the game.
 * Items can have effects on the player or enemies depending on their type.
 */
public class Item {
	private String name;
	private int cost;
	private String description;
	private Random r = new Random();

	/**
     	* Default constructor for Item.
     	*/
	public Item() {}

	/**
     	* Constructs an Item with a given name, cost, and description.
     	* @param name The name of the item
     	* @param cost The gold cost of the item
     	* @param description A description of what the item does
     	*/
	public Item(String name, int cost, String description) {
		this.name = name;
		this.cost = cost;
		this.description = description;
	}

	/**
     	* Applies the effect of the item to the player and optionally enemies.
     	* @param player The player using the item
     	* @param item The item being used
     	* @param enemies The list of enemies affected (if applicable)
     	*/
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

	/**
     	* Gets the name of the item.
     	* @return The item's name
     	*/
	public String getName() {
		return this.name;
	}

	/**
     	* Gets the cost of the item.
     	* @return The item's cost
     	*/
	public int getCost() {
		return this.cost;
	}

	/**
     	* Gets the description of the item.
     	* @return The item's description
     	*/
	public String getDescription() {
		return this.description;
	}
	
	
	
	
}
