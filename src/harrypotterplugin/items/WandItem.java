package harrypotterplugin.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import harrypotterplugin.main.HarryPotterPlugin;
import harrypotterplugin.utilities.ExtendedItemStack;
import harrypotterplugin.utilities.SpellProjectile;

public class WandItem extends ExtendedItemStack implements Listener {
	
	private boolean cooldown = false;
	
	public WandItem() {
		super("Wand", Material.CARROT_ON_A_STICK, 1);
		setLeftClickAction(event -> {
			if (!cooldown) {
				cooldown = true;
				Bukkit.getScheduler().runTaskLater(HarryPotterPlugin.getInstance(), () -> cooldown = false, 10);
				SpellProjectile spellProjectile = new SpellProjectile(1, 10, event.getPlayer().getEyeLocation(), event.getPlayer().getEyeLocation().getDirection());
				spellProjectile.setHitEntityAction(entity -> {
					if (entity instanceof LivingEntity) {
						((LivingEntity)entity).damage(1000);
					}
				});
				spellProjectile.setHitBlockAction(block -> {
					System.out.println("Test2");
				});
				spellProjectile.setOutrangedAction(() -> {
					System.out.println("Test");
				});
				spellProjectile.launch(event.getPlayer());
			}
			
		});
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent event) {
		if(event.getCause() == DamageCause.ENTITY_ATTACK && event.getDamager() instanceof Player && ExtendedItemStack.isItem(((Player)event.getDamager()).getInventory().getItemInMainHand(), Material.CARROT_ON_A_STICK, 1)) event.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if(ExtendedItemStack.isItem(event.getPlayer().getInventory().getItemInMainHand(), Material.CARROT_ON_A_STICK, 1)) event.setCancelled(true);
	}

}
