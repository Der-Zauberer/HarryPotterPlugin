package harrypotterplugin.handler;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import harrypotterplugin.utilities.ExtendedItem;

public class ItemHandler implements Listener {

	private static ArrayList<ExtendedItem> items = new ArrayList<>();
	private static ArrayList<NamespacedKey> namespacedKeys = new ArrayList<>();
	
	public static void registerItem(ExtendedItem item) {
		items.add(item);
	}
	
	public static ArrayList<ExtendedItem> getItems() {
		return items;
	}
	
	public static void registerNameSpaceKey(NamespacedKey namespacedKey) {
		namespacedKeys.add(namespacedKey);
	}
	
	public static ArrayList<NamespacedKey> getNamespacedKeys() {
		return namespacedKeys;
	}
	
	@EventHandler
	public static void onPlayerInteract(PlayerInteractEvent event) {
		ExtendedItem item;
		if ((item = getItem(event.getItem())) != null) {
			if (item.getOnInteract() != null) item.getOnInteract().onAction(event);
			if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
				if (item.getOnLeftClick() != null) item.getOnLeftClick().onAction(event);
			} else if  (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (item.getOnRightClick() != null) item.getOnRightClick().onAction(event);
			}
		}
	}
	
	public static ExtendedItem getItem(ItemStack itemStack) {
		for (ExtendedItem item : items) {
			if (itemStack != null && item.getType() == itemStack.getType() && itemStack.hasItemMeta() && itemStack.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == itemStack.getItemMeta().getCustomModelData()) {
				return item;
			}
		}
		return null;
	}
	
	public static boolean isItem(ItemStack itemstack, Material material, int custommodeldata) {
		if(itemstack != null && itemstack.getType() == material && itemstack.hasItemMeta() && itemstack.getItemMeta().hasCustomModelData() && itemstack.getItemMeta().getCustomModelData() == custommodeldata) {
			return true;
		} else {
			return false;
		}
	}
	
}
