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
import harrypotterplugin.utilities.ExtendedItem;

public class BroomstickItem extends ExtendedItem {
	
	public enum BroomstickType {SHOOTING_STAR, NIMBUS_2000, NIMBUS_2001, FIREBOLD};
	private static HashMap<Player, Integer> states = new HashMap<>();
	private static HashMap<Player, Integer> tasks = new HashMap<>();

	public BroomstickItem(BroomstickType type) {
		super("Broomstick", Material.CARROT_ON_A_STICK, 0);
		switch (type) {
		case SHOOTING_STAR:
			setDisplayName("Shooting Star");
			setLore(ChatColor.GRAY + "Produced by Universal Brooms Ltd in 1955");
			setCustomModelData(11);
			break;
		case NIMBUS_2000:
			setDisplayName("Nimbus 2000");
			setLore(ChatColor.GRAY + "Produced by Nimbus Racing Broom Company in 1991");
			setCustomModelData(12);
			break;
		case NIMBUS_2001:
			setDisplayName("Nimbus 2001");
			setLore(ChatColor.GRAY + "Produced by Nimbus Racing Broom Company in 1992");
			setCustomModelData(13);
			break;
		case FIREBOLD:
			setDisplayName("Firebold");
			setLore(ChatColor.GRAY + "Produced by Randolph Spudmore in 1993");
			setCustomModelData(14);
			break;
		default:
			break;
		}
		setOnInteract(event -> {
			Player player = event.getPlayer();
			ItemStack itemStack = event.getItem();
			Action action = event.getAction();
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
					    	if((!player.getInventory().getItemInMainHand().equals(itemStack) && !player.getInventory().getItemInOffHand().equals(itemStack)) || player.isOnGround()) {
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
				player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 100000, getLevitationStrenght(itemStack)));
				if(itemStack.getItemMeta().getCustomModelData() != 11) player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, getSpeedStrenght(itemStack)));
				player.removePotionEffect(PotionEffectType.SLOW_FALLING);
				break;
			case 2:
				player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 100000, getFallingStrenght(itemStack)));
				if(itemStack.getItemMeta().getCustomModelData() != 11) player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, getSpeedStrenght(itemStack)));
				player.removePotionEffect(PotionEffectType.LEVITATION);
				break;
			default: break;
			}
		});
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
