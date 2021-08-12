package harrypotterplugin.handler;

import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import harrypotterplugin.utilities.UsableItem;

public class ItemHandler implements Listener {
	
	private static ArrayList<UsableItem> usableitems = new ArrayList<>();
	private static ArrayList<NamespacedKey> namespacekeys = new ArrayList<>();
	
	public static void registerItem(UsableItem usableitem) {
		usableitems.add(usableitem);
	}
	
	public static ArrayList<UsableItem> getUsableitems() {
		return usableitems;
	}
	
	public static void registerNameSpaceKey(NamespacedKey key) {
		namespacekeys.add(key);
	}
	
	public static ArrayList<NamespacedKey> getNamespacekeys() {
		return namespacekeys;
	}
	
	public static UsableItem getItem(String name) {
		String realname = ChatColor.stripColor(name);
		String namewithoutspaces = realname.replace("_", " ");
		for(UsableItem usableitem : usableitems) {
			String itemname = ChatColor.stripColor(usableitem.getItemMeta().getDisplayName());
			if(itemname.equalsIgnoreCase(name) || itemname.equalsIgnoreCase(namewithoutspaces)) {
				return usableitem;
			}
		}
		return null;
	}
	
	public static UsableItem getItemFunction(ItemStack itemstack) {
		for (UsableItem usableitem : usableitems) {
			if(usableitem.getType().equals(itemstack.getType())) {
				if(itemstack.getItemMeta().hasCustomModelData() && usableitem.getItemMeta().getCustomModelData() == itemstack.getItemMeta().getCustomModelData()) {
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
		if(event.getHand() == EquipmentSlot.HAND) {
			if(itemfunction != null) {
				itemfunction.onItemUse(event.getPlayer(), itemstack, event.getAction());
			}
		}
	}
	
	public static boolean isItem(ItemStack itemstack, Material material, int custommodeldata) {
		if(itemstack != null && itemstack.getType() == material && itemstack.hasItemMeta() && itemstack.getItemMeta().hasCustomModelData() && itemstack.getItemMeta().getCustomModelData() == custommodeldata) {
			return true;
		} else {
			return false;
		}
	}
	
}