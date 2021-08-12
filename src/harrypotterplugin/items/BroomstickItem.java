package harrypotterplugin.items;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import harrypotterplugin.main.HarryPotterPlugin;
import harrypotterplugin.utilities.ItemBuilder;
import harrypotterplugin.utilities.UsableItem;

public class BroomstickItem extends UsableItem {
	
	public enum BroomstickType {SHOOTING_STAR, NIMBUS_2000, NIMBUS_2001, FIREBOLD};
	private static HashMap<Player, Integer> states = new HashMap<>();
	private static HashMap<Player, Integer> tasks = new HashMap<>();

	public BroomstickItem(BroomstickType type) {
		super(Material.CARROT_ON_A_STICK);
		ItemBuilder itembuilder = new ItemBuilder();
		switch (type) {
		case SHOOTING_STAR:
			itembuilder.setDisplayName("Shooting Star");
			itembuilder.setLore(ChatColor.GRAY + "Produced by Universal Brooms Ltd in 1955");
			itembuilder.setCustomModelData(11);
			break;
		case NIMBUS_2000:
			itembuilder.setDisplayName("Nimbus 2000");
			itembuilder.setLore(ChatColor.GRAY + "Produced by Nimbus Racing Broom Company in 1991");
			itembuilder.setCustomModelData(12);
			break;
		case NIMBUS_2001:
			itembuilder.setDisplayName("Nimbus 2001");
			itembuilder.setLore(ChatColor.GRAY + "Produced by Nimbus Racing Broom Company in 1992");
			itembuilder.setCustomModelData(13);
			break;
		case FIREBOLD:
			itembuilder.setDisplayName("Firebold");
			itembuilder.setLore(ChatColor.GRAY + "Produced by Randolph Spudmore in 1993");
			itembuilder.setCustomModelData(14);
			break;
		default:
			break;
		}
		itembuilder.buildItem(this);
	}

	@Override
	public void onItemUse(Player player, ItemStack itemstack, Action action) {
		if(states.get(player) == null) {
			states.put(player, 0);
		}
		int state = states.get(player);
		if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
			if(state == 0 || state == 2) {
				state = 1;
			} else {
				state = 2;
			}
			if(tasks.get(player) == null) {
				int taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(HarryPotterPlugin.getInstance(), new Runnable() {
				    @SuppressWarnings("deprecation")
					@Override
				    public void run() {
				    	if((!player.getInventory().getItemInMainHand().equals(itemstack) && !player.getInventory().getItemInOffHand().equals(itemstack)) || player.isOnGround()) {
				    		player.removePotionEffect(PotionEffectType.LEVITATION);
							player.removePotionEffect(PotionEffectType.SLOW_FALLING);
							player.removePotionEffect(PotionEffectType.SPEED);
				    		Bukkit.getScheduler().cancelTask(tasks.get(player));
				    		tasks.remove(player);
				    		states.remove(player);
				    	}
				    }
				}, 10L, 5L);
				tasks.put(player, taskid);
			}
		} else if(action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
			state = 0;
		}
		states.put(player, state);
		switch (state) {
		case 0:
			player.removePotionEffect(PotionEffectType.LEVITATION);
			player.removePotionEffect(PotionEffectType.SLOW_FALLING);
			player.removePotionEffect(PotionEffectType.SPEED);
			break;
		case 1:
			player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 100000, getLevitationStrenght(itemstack)));
			if(itemstack.getItemMeta().getCustomModelData() != 11) player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, getSpeedStrenght(itemstack)));
			player.removePotionEffect(PotionEffectType.SLOW_FALLING);
			break;
		case 2:
			player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 100000, getFallingStrenght(itemstack)));
			if(itemstack.getItemMeta().getCustomModelData() != 11) player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, getSpeedStrenght(itemstack)));
			player.removePotionEffect(PotionEffectType.LEVITATION);
			break;
		default: break;
		}
			
	}
	
	private int getLevitationStrenght(ItemStack itemstack) {
		switch (itemstack.getItemMeta().getCustomModelData()) {
		case 11: return 1;
		case 12: return 2;
		case 13: return 3;
		case 14: return 5;
		default: return 0;
		}
	}
	
	private int getFallingStrenght(ItemStack itemstack) {
		switch (itemstack.getItemMeta().getCustomModelData()) {
		case 11: return 5;
		case 12: return 3;
		case 13: return 2;
		case 14: return 1;
		default: return 0;
		}
	}

	private int getSpeedStrenght(ItemStack itemstack) {
		switch (itemstack.getItemMeta().getCustomModelData()) {
		case 12: return 10;
		case 13: return 20;
		case 14: return 40;
		default: return 0;
		}
	}
	
}
