package harrypotterplugin.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import harrypotterplugin.main.HarryPotterPlugin;
import harrypotterplugin.utilities.ItemBuilder;
import harrypotterplugin.utilities.UsableItem;

public class TimeTurnerItem extends UsableItem {
	
	private static boolean inuse = false;
	private int taskid = 0;

	public TimeTurnerItem() {
		super(Material.CARROT_ON_A_STICK);
		ItemBuilder itembuilder = new ItemBuilder("Time-Turner");
		itembuilder.setLore(ChatColor.GRAY + "Can set the time back");
		itembuilder.setCustomModelData(21);
		itembuilder.buildItem(this);
	}

	@Override
	public void onItemUse(Player player, ItemStack itemstack, Action action) {
		if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
			if(!inuse) {
				inuse = true;
				taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(HarryPotterPlugin.getInstance(), new Runnable() {
				    @Override
				    public void run() {
				    	if(inuse) {
				    		if(player.getWorld().getTime() > 60) {
					        	player.getWorld().setTime(player.getWorld().getTime() - 60);
					        } else {
					        	player.getWorld().setTime(23900);
					        }
				    	} else {
				    		Bukkit.getScheduler().cancelTask(taskid);
				    	}
				        
				    }
				}, 0L, 1L);
			} else {
				inuse = false;
			}
		}
	}

}
