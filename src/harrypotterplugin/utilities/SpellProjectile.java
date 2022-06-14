package harrypotterplugin.utilities;

import java.util.Collection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import harrypotterplugin.actions.SpellProjectileHitBlockAction;
import harrypotterplugin.actions.SpellProjectileHitEntityAction;
import harrypotterplugin.main.HarryPotterPlugin;

public class SpellProjectile {
	
	private int scedulerId;
	private int counter;
	private boolean alive;
	private final int speed;
	private final int distance;
	private Location location;
	private Vector vector;
	private SpellProjectileHitBlockAction hitBlockAction;
	private SpellProjectileHitEntityAction hitEntityAction;
	private Runnable outrangedAction;
	
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
		scedulerId = Bukkit.getScheduler().scheduleSyncRepeatingTask(HarryPotterPlugin.getInstance(), () -> {
			if (alive) {
				location.getWorld().spawnParticle(Particle.HEART, location, 2);
				location = location.add(vector);
				counter++;
				Block block = location.getBlock();
				Collection<Entity> entities = location.getWorld().getNearbyEntities(location, 0.1, 0.1, 0.1);
				if (block.getType() != Material.AIR) {
					kill();
					if (hitBlockAction != null) hitBlockAction.onAction(block);
				} else if ((player != null && (entities.size() > 0 && !entities.contains(player)) || entities.size() > 1) || player == null && entities.size() > 0) {
					kill();
					if (hitEntityAction != null) {
						for (Entity entity : location.getWorld().getNearbyEntities(location, 1, 1, 1)) {
							if (entities != player) hitEntityAction.onAction(entity);
						}
					}
				} else if (counter > distance) {
					kill();
					if (outrangedAction != null) outrangedAction.run();
				}
			}
		}, 2, speed);
	}
	
	public void kill() {
		alive = false;
		Bukkit.getScheduler().cancelTask(scedulerId);
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
	
	public void setHitEntityAction(SpellProjectileHitEntityAction hitEntityAction) {
		this.hitEntityAction = hitEntityAction;
	}
	
	public SpellProjectileHitEntityAction getHitEntityAction() {
		return hitEntityAction;
	}
	
	public void setHitBlockAction(SpellProjectileHitBlockAction hitBlockAction) {
		this.hitBlockAction = hitBlockAction;
	}
	
	public SpellProjectileHitBlockAction getHitBlockAction() {
		return hitBlockAction;
	}
	
	public void setOutrangedAction(Runnable outrangedAction) {
		this.outrangedAction = outrangedAction;
	}
	
	public Runnable getOutrangedAction() {
		return outrangedAction;
	}

}