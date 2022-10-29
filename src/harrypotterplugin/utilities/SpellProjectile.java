package harrypotterplugin.utilities;

import java.util.Collection;
import java.util.function.BiConsumer;
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
    private final boolean stopOnHitEntity;
    private final Particle particle;
    private Consumer<Block> hitBlockAction;
    private BiConsumer<Player, Entity> hitEntityAction;
    private Runnable outRangedAction;
    
    private Location location;
    private Vector vector;

    public SpellProjectile(int speed, int distance, boolean stopOnHitEntity, Particle particle) {
        counter = 0;
        alive = false;
        this.speed = speed;
        this.distance = distance;
        this.stopOnHitEntity = stopOnHitEntity;
        this.particle = particle;
    }

    public void launch(Player player) {
        launch(player, player.getLocation(), player.getLocation().getDirection());
    }

    public void launch(Player player, Location location, Vector vector) {
    	this.location = location;
    	this.vector = vector;
    	if (this.location == null) this.location = player.getLocation();
    	if (this.vector == null) this.vector = player.getLocation().getDirection();
        alive = true;
        schedulerId = Bukkit.getScheduler().scheduleSyncRepeatingTask(HarryPotterPlugin.getInstance(), () -> {
            if (alive) {
                if (this.location.getWorld() == null) return;
                this.location.getWorld().spawnParticle(particle, this.location, 2);
                this.location.add(this.vector);
                counter++;
                final Block block = this.location.getBlock();
                if (this.location.getWorld() == null) return;
                final Collection<Entity> entities = this.location.getWorld().getNearbyEntities(this.location, 0.1, 0.1, 0.1);
                if (block.getType() != Material.AIR) {
                    kill();
                    if (hitBlockAction != null) hitBlockAction.accept(block);
                } else if ((player != null && (entities.size() > 0 && !entities.contains(player)) || entities.size() > 1) || player == null && entities.size() > 0) {
                    if (stopOnHitEntity) kill();
                    if (hitEntityAction != null) {
                        for (Entity entity : this.location.getWorld().getNearbyEntities(this.location, 1, 1, 1)) {
                            if (entities != player) hitEntityAction.accept(player, entity);
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
    
	public boolean stopOnHitEntity() {
		return stopOnHitEntity;
	}

    public void setHitBlockAction(Consumer<Block> hitBlockAction) {
		this.hitBlockAction = hitBlockAction;
	}
    
    public Consumer<Block> getHitBlockAction() {
		return hitBlockAction;
	}
    
	public void setHitEntityAction(BiConsumer<Player, Entity> hitEntityAction) {
		this.hitEntityAction = hitEntityAction;
	}
	
	public BiConsumer<Player, Entity> getHitEntityAction() {
		return hitEntityAction;
	}

    public void setOutRangedAction(Runnable outRangedAction) {
        this.outRangedAction = outRangedAction;
    }

    public Runnable getOutRangedAction() {
        return outRangedAction;
    }

}
