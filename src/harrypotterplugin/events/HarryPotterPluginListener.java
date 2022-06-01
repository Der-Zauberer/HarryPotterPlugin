package harrypotterplugin.events;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import harrypotterplugin.handler.ItemHandler;

public class HarryPotterPluginListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.getPlayer().discoverRecipes(ItemHandler.getNamespacedKeys());
	}
	
	@EventHandler
	public void onEntityTarget(EntityTargetEvent event) {
		if(event.getEntityType() == EntityType.PIG && event.getTarget() instanceof Player) {
			ItemStack mainItem = ((Player)event.getTarget()).getInventory().getItemInMainHand();
			ItemStack secondaryItem = ((Player)event.getTarget()).getInventory().getItemInOffHand();
			if(mainItem.getType() == Material.CARROT_ON_A_STICK && mainItem.hasItemMeta() && mainItem.getItemMeta().hasCustomModelData() && mainItem.getItemMeta().getCustomModelData() != 0) {
				event.setCancelled(true);
			} else if(secondaryItem.getType() == Material.CARROT_ON_A_STICK && secondaryItem.hasItemMeta() && secondaryItem.getItemMeta().hasCustomModelData() && secondaryItem.getItemMeta().getCustomModelData() != 0) {
				event.setCancelled(true);
			}
		}
		
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player && event.getCause() == DamageCause.FALL && event.getDamage() != 0) {
			ArrayList<ItemStack> brokenItems = new ArrayList<>();
			for(ItemStack itemStack : ((Player)event.getEntity()).getInventory()) {
				if(itemStack != null && ItemHandler.isItem(itemStack, Material.CARROT_ON_A_STICK, 22)) {
					double damage = event.getDamage();
					if(itemStack.getItemMeta().hasEnchant(Enchantment.DURABILITY)) {
						if(itemStack.getItemMeta().getEnchantLevel(Enchantment.DURABILITY) == 1) {
							damage = damage / 1.5;
						} else if(itemStack.getItemMeta().getEnchantLevel(Enchantment.DURABILITY) == 2) {
							damage = damage / 2;
						} else if(itemStack.getItemMeta().getEnchantLevel(Enchantment.DURABILITY) == 3) {
							damage = damage / 2.5;
						}
					}
					if((itemStack.getDurability() + damage < 25)) {
						itemStack.setDurability((short)(itemStack.getDurability() + damage));
						event.setDamage(0);
					} else {
						event.setDamage(event.getDamage() - (25 - itemStack.getDurability()));
						itemStack.setDurability((short) 25);
						brokenItems.add(itemStack);
					}
				}
			}
			for (int i = 0; i < brokenItems.size(); i++) {
				((Player)event.getEntity()).getInventory().remove(brokenItems.get(i));
			}
		}
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent event) {
		if(event.getCause() == DamageCause.ENTITY_ATTACK) {
			if(event.getDamager() instanceof Player && ItemHandler.isItem(((Player)event.getDamager()).getInventory().getItemInMainHand(), Material.CARROT_ON_A_STICK, 1)) {
				event.setCancelled(true);
			}
		}
		
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if(ItemHandler.isItem(event.getPlayer().getInventory().getItemInMainHand(), Material.CARROT_ON_A_STICK, 1)) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {
		if(event.getEntity().getScoreboardTags().contains("spell")) {
			if(event.getHitEntity() != null) {
				event.getHitEntity().setVisualFire(true);
			}
			event.setCancelled(true);
		}
	}

}
