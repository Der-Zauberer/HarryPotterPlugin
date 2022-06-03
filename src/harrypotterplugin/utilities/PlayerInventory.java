package harrypotterplugin.utilities;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import harrypotterplugin.actions.InventoryClickAction;

public class PlayerInventory implements Listener {
	
	private boolean fixed;
	private Inventory inventory;
	private Player player;
	private HashMap<Integer, InventoryClickAction> actions;
	
	private static ArrayList<PlayerInventory> inventories = new ArrayList<>();
	private static PlayerInventory instance = new PlayerInventory();

	private PlayerInventory() {};
	
	public PlayerInventory(Player player, int height, String name) {
		this.fixed = false;
		this.player = player;
		this.inventory = Bukkit.createInventory(player, height * 9, name);
		this.actions = new HashMap<>();
	}
	
	public void setItem(int slot, ItemStack itemStack, InventoryClickAction action) {
		inventory.setItem(slot, itemStack);
		actions.put(slot, action);
	}
	
	public void setItem(int slot, ItemStack itemStack) {
		inventory.setItem(slot, itemStack);
		if (actions.containsKey(slot)) actions.remove(slot);
	}
	
	public void open() {
		player.openInventory(inventory);
		inventories.add(this);
	}
	
	public void close() {
		player.closeInventory();
		inventories.remove(this);
	}
	
	public void clear() {
		actions.clear();
		inventory.clear();
	}
	
	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}
	
	public boolean isFixed() {
		return fixed;
	}
	
	@EventHandler
	public static void onInventoryClicked(InventoryClickEvent event) {
		try {
			if(event.getCurrentItem() != null) {
				for(PlayerInventory inventory : inventories) {
					if (event.getClickedInventory() == inventory.inventory) {
						if (inventory.actions.containsKey(event.getSlot())) inventory.actions.get(event.getSlot()).onAction(event);
						if (inventory.isFixed()) event.setCancelled(true);
						return;
					} else if (inventory.isFixed() && event.getWhoClicked().getOpenInventory().getTopInventory() == inventory.inventory) {
						event.setCancelled(true);
						return;
					}
				}
			}
		} catch (ConcurrentModificationException exception) {}
	}
	
	@EventHandler
	public static void onInventoryClosed(InventoryCloseEvent event) {
		for (PlayerInventory inventory : inventories) {
			if (event.getInventory() == inventory.inventory) {
				inventories.remove(inventory);
				return;
			}
		}
	}
	
	public static PlayerInventory getInstance() {
		return instance;
	}
	
}
