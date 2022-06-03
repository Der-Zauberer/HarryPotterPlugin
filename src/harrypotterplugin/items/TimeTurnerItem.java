package harrypotterplugin.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import harrypotterplugin.main.HarryPotterPlugin;
import harrypotterplugin.utilities.ExtendedItemStack;

public class TimeTurnerItem extends ExtendedItemStack {
	
	private static boolean isInUse = false;
	private int taskid = 0;

	public TimeTurnerItem() {
		super("Time-Turner", Material.CARROT_ON_A_STICK, 21);
		setLore(ChatColor.GRAY + "Can set the time back");
		setOnRightClick(event -> {
			if(!isInUse) {
				isInUse = true;
				taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(HarryPotterPlugin.getInstance(), () -> {
					if(isInUse) {
			    		if(event.getPlayer().getWorld().getTime() > 60) {
			    			event.getPlayer().getWorld().setTime(event.getPlayer().getWorld().getTime() - 60);
				        } else {
				        	event.getPlayer().getWorld().setTime(23900);
				        }
			    	} else {
			    		Bukkit.getScheduler().cancelTask(taskid);
			    	}
				}, 0L, 1L);
			} else {
				isInUse = false;
			}
		});
	}

}
