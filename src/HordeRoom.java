/**
 * The HordeRoom class represents a combat-focused room where the player faces multiple goblins.
 * The player must survive a series of combat rounds against three enemies. Mages have a special
 * area-of-effect ability that allows them to damage all goblins at once. The goal is to defeat all
 * enemies while managing health and using inventory items wisely.
 * Upon success, the player receives a gold reward and continues the journey.
 */
public class HordeRoom extends Room {

    /**
     * Constructs a HordeRoom instance and initializes its state.
     */
    public HordeRoom() {
        super();
    }

    /**
     * Executes the room's logic by initiating a battle between the player and three goblins.
     * Handles class-based combat mechanics (e.g., mage AOE spells), enemy retaliation,
     * player health management, and end-of-battle gold rewards.
     *
     * @param player The player entering the room and fighting the goblins
     */
    public void playRoom(Player player) {
        System.out.println("You enter a dark chamber and are confronted by three goblins!");
        Enemy[] goblins = new Enemy[3];
        for (int i = 0; i < 3; i++) {
            goblins[i] = new Enemy("goblin");
        }
        System.out.println("The goblins screech and charge at you!");

        while (player.isAlive() && anyGoblinAlive(goblins)) {
            // Player's turn
            if (player.getCharacterClass().equals("mage")) {
                // Mage's splash damage affects all goblins
                System.out.println("You cast a powerful spell that hits all goblins!");
                for (Enemy goblin : goblins) {
                    if (goblin.health() > 0) {
                        System.out.println("The spell hits a goblin for " + player.getDamage() + " damage!");
                        goblin.takeDamage(player.getDamage());
                    }
                }
            } else {
                for (int i = 0; i < goblins.length; i++) {
                    if (goblins[i].health() > 0) {
                        System.out.println("You strike goblin " + (i + 1) + " for " + player.getDamage() + " damage!");
                        goblins[i].takeDamage(player.getDamage());
                        break;
                    }
                }
            }

            // Check if all goblins are defeated
            if (!anyGoblinAlive(goblins)) {
                System.out.println("You have defeated all the goblins!");
                int goldReward = 30; // Reward for defeating all goblins
                player.addGold(goldReward);
                System.out.println("You found " + goldReward + " gold on the goblins!");
                System.out.println("Your total gold: " + player.getGold());
                break;
            }

            // Goblins attack back
            for (Enemy goblin : goblins) {
                if (goblin.health() > 0) {
                    int goblinAttack = goblin.attack();
                    System.out.println("A goblin hits you for " + goblinAttack + " damage!");
                    player.takeDamage(goblinAttack);
                    if (!player.isAlive()) {
                        System.out.println("The goblins have defeated you...");
                        break;
                    }
                }
            }

            // Display status
            System.out.println("Your health: " + player.getHealth());
            System.out.print("Goblins' health: ");
            for (int i = 0; i < goblins.length; i++) {
                System.out.print("Goblin " + (i + 1) + ": " + goblins[i].health() + " ");
            }
            System.out.println();
        }
        System.out.println("You leave the horde room.");
    }

    /**
     * Checks whether any goblins in the array are still alive.
     *
     * @param goblins Array of Enemy objects representing goblins
     * @return true if at least one goblin has health > 0, false otherwise
     */
    private boolean anyGoblinAlive(Enemy[] goblins) {
        for (Enemy goblin : goblins) {
            if (goblin.health() > 0) {
                return true;
            }
        }
        return false;
    }
}
