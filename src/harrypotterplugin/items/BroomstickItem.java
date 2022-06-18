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
import harrypotterplugin.utilities.ExtendedItemStack;

public class BroomstickItem extends ExtendedItemStack {
	
	public enum BroomstickType {
		SHOOTING_STAR("Shooting Star", ChatColor.GRAY + "Produced by Universal Brooms Ltd in 1955", 11, 0, 1, 5),
		NIMBUS_2000("Nimbus 2000", ChatColor.GRAY + "Produced by Nimbus Racing Broom Company in 1991", 12, 10, 2, 3),
		NIMBUS_2001("Nimbus 2001", ChatColor.GRAY + "Produced by Nimbus Racing Broom Company in 1992", 13, 20, 3, 2),
		FIREBOLD("Firebold", ChatColor.GRAY + "Produced by Randolph Spudmore in 1993", 14, 40, 5, 1);

		private final String displayName;
		private final String lore;
		private final int customModelData;
		private final int speed;
		private final int levitationStrenght;
		private final int fallingStrenght;
		
		BroomstickType(String displayName, String lore, int customModelData, int speed, int levitationStrenght, int fallingStrenght) {
			this.displayName = displayName;
			this.lore = lore;
			this.customModelData = customModelData;
			this.speed = speed;
			this.levitationStrenght = levitationStrenght;
			this.fallingStrenght = fallingStrenght;
		}
	};
	
	private static final HashMap<Player, Integer> states = new HashMap<>();
	private static final HashMap<Player, Integer> tasks = new HashMap<>();

	@SuppressWarnings("deprecation")
	public BroomstickItem(BroomstickType type) {
		super(type.displayName, Material.CARROT_ON_A_STICK, type.customModelData);
		setLore(type.lore);
		registerEvents();
		setInteractAction(event -> {
			final Player player = event.getPlayer();
			final ItemStack itemStack = event.getItem();
			final BroomstickType broomstickType = BroomstickItem.getBroomstickType(itemStack.getItemMeta().getCustomModelData());
			final Action action = event.getAction();
			if (states.get(player) == null) states.put(player, 0);
			int state = states.get(player);
			if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
				if (state == 0 || state == 2) state = 1;
				else state = 2;
				if (tasks.get(player) == null) {
					final int taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(HarryPotterPlugin.getInstance(), () ->  {
						if ((!player.getInventory().getItemInMainHand().equals(itemStack) && !player.getInventory().getItemInOffHand().equals(itemStack)) || player.isOnGround()) {
				    		player.removePotionEffect(PotionEffectType.LEVITATION);
							player.removePotionEffect(PotionEffectType.SLOW_FALLING);
							player.removePotionEffect(PotionEffectType.SPEED);
				    		Bukkit.getScheduler().cancelTask(tasks.get(player));
				    		tasks.remove(player);
				    		states.remove(player);
				    	}
					}, 10L, 5L);
					tasks.put(player, taskid);
				}
			} else if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
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
				player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 100000, broomstickType.levitationStrenght));
				if(broomstickType != BroomstickType.SHOOTING_STAR) player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, broomstickType.speed));
				player.removePotionEffect(PotionEffectType.SLOW_FALLING);
				break;
			case 2:
				player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 100000, broomstickType.fallingStrenght));
				if(broomstickType != BroomstickType.SHOOTING_STAR) player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, broomstickType.speed));
				player.removePotionEffect(PotionEffectType.LEVITATION);
				break;
			default: break;
			}
		});
	}
	
	public static BroomstickType getBroomstickType(int customModelData) {
		switch (customModelData) {
		case 11: return BroomstickType.SHOOTING_STAR;
		case 12: return BroomstickType.NIMBUS_2000;
		case 13: return BroomstickType.NIMBUS_2001;
		case 14: return BroomstickType.FIREBOLD;
		default: return null;
		}
	}
	
}
