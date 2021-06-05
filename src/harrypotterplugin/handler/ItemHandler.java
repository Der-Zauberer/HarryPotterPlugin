package harrypotterplugin.handler;

import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import harrypotterplugin.utilities.UsableItem;

public class ItemHandler implements Listener {
	
	private static ArrayList<UsableItem> usableitems = new ArrayList<>();
	
	public static void registerItem(UsableItem usableitem) {
		usableitems.add(usableitem);
	}
	
	public static ArrayList<UsableItem> getUsableitems() {
		return usableitems;
	}
	
	public static UsableItem getItem(String name) {
		String realname = ChatColor.stripColor(name);
		String namewithoutspaces = realname.replace("_", " ");
		for(UsableItem usableitem : usableitems) {
			String itemname = ChatColor.stripColor(usableitem.getItem().getItemMeta().getDisplayName());
			if(itemname.equalsIgnoreCase(name) || itemname.equalsIgnoreCase(namewithoutspaces)) {
				return usableitem;
			}
		}
		return null;
	}
	
	public static UsableItem getItemFunction(ItemStack itemstack) {
		for (UsableItem usableitem : usableitems) {
			if(usableitem.getItem().getType().equals(itemstack.getType())) {
				if(itemstack.getItemMeta().hasCustomModelData() && usableitem.getItem().getItemMeta().getCustomModelData() == itemstack.getItemMeta().getCustomModelData()) {
					return usableitem;
				}
			}
		}
		return null;
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		ItemStack itemstack = event.getPlayer().getInventory().getItemInMainHand();
		UsableItem itemfunction = getItemFunction(itemstack);
		if(itemfunction != null) {
			itemfunction.onItemUse(event.getPlayer(), itemstack, event.getAction());
		}
	}
	
}