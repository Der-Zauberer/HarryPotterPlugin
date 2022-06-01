package harrypotterplugin.handler;

import java.util.ArrayList;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import harrypotterplugin.utilities.ExtendedItem;

public class ExtendedItemHandler implements Listener {

	private static ArrayList<ExtendedItem> items = new ArrayList<>();
	
	public static void registerItem(ExtendedItem item) {
		items.add(item);
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
			if (item.getType() == itemStack.getType() && itemStack.hasItemMeta() && itemStack.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == itemStack.getItemMeta().getCustomModelData()) {
				return item;
			}
		}
		return null;
	}
	
}
