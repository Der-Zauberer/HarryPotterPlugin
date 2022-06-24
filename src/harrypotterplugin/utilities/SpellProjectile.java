package harrypotterplugin.utilities;

import java.util.Collection;
import java.util.function.Consumer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import harrypotterplugin.main.HarryPotterPlugin;

public class SpellProjectile {

    private int schedulerId;
    private int counter;
    private boolean alive;
    private final int speed;
    private final int distance;
    private Location location;
    private final Vector vector;
    private Consumer<Block> hitBlockAction;
    private Consumer<Entity> hitEntityAction;
    private Runnable outRangedAction;

    public SpellProjectile(int speed, int distance, Location location, Vector vector) {
        counter = 0;
        alive = false;
        this.speed = speed;
        this.distance = distance;
        this.location = location;
        this.vector = vector;
    }

    public void launch() {
        launch(null);
    }

    public void launch(Player player) {
        alive = true;
        schedulerId = Bukkit.getScheduler().scheduleSyncRepeatingTask(HarryPotterPlugin.getInstance(), () -> {
            if (alive) {
                if (location.getWorld() == null) return;
                location.getWorld().spawnParticle(Particle.HEART, location, 2);
                location = location.add(vector);
                counter++;
                Block block = location.getBlock();
                if (location.getWorld() == null) return;
                Collection<Entity> entities = location.getWorld().getNearbyEntities(location, 0.1, 0.1, 0.1);
                if (block.getType() != Material.AIR) {
                    kill();
                    if (hitBlockAction != null) hitBlockAction.accept(block);
                } else if ((player != null && (entities.size() > 0 && !entities.contains(player)) || entities.size() > 1) || player == null && entities.size() > 0) {
                    kill();
                    if (hitEntityAction != null) {
                        for (Entity entity : location.getWorld().getNearbyEntities(location, 1, 1, 1)) {
                            if (entities != player) hitEntityAction.accept(entity);
                        }
                    }
                } else if (counter > distance) {
                    kill();
                    if (outRangedAction != null) outRangedAction.run();
                }
            }
        }, 2, speed);
    }

    public void kill() {
        alive = false;
        Bukkit.getScheduler().cancelTask(schedulerId);
    }

    public boolean isAlive() {
        return alive;
    }

    public int getSpeed() {
        return speed;
    }

    public Location getLocation() {
        return location;
    }

    public Vector getVector() {
        return vector;
    }

    public void setHitBlockAction(Consumer<Block> hitBlockAction) {
		this.hitBlockAction = hitBlockAction;
	}
    
    public Consumer<Block> getHitBlockAction() {
		return hitBlockAction;
	}
    
    public void setHitEntityAction(Consumer<Entity> hitEntityAction) {
		this.hitEntityAction = hitEntityAction;
	}
    
    public Consumer<Entity> getHitEntityAction() {
		return hitEntityAction;
	}

    public void setOutRangedAction(Runnable outRangedAction) {
        this.outRangedAction = outRangedAction;
    }

    public Runnable getOutRangedAction() {
        return outRangedAction;
    }

}
