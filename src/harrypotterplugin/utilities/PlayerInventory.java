package harrypotterplugin.utilities;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import harrypotterplugin.actions.InventoryClickAction;
import harrypotterplugin.handler.InventoryHandler;

public class PlayerInventory {
	
	public enum ItemOption{DRAGABLE, GETABLE, FIXED};
	
	private Inventory inventory;
	private ItemOption itemoption;
	private Player player;
	private HashMap<ItemStack, InventoryClickAction> events = new HashMap<>();

	public PlayerInventory(Player player, int number, String name) {
		this.player = player;
		inventory = Bukkit.createInventory(player, number, name);
	}
	
	public void setItem(int slot, ItemStack itemStack, InventoryClickAction action) {
		inventory.setItem(slot, itemStack);
		events.put(itemStack, action);
	}
	
	public void setItem(int slot, ItemStack itemStack) {
		inventory.setItem(slot, itemStack);
	}
	
	public void setEvent(ItemStack itemStack, InventoryClickAction action) {
		events.put(itemStack, action);
	}
	
	public void onItemClicked(ItemStack itemStack) {
		if(events.containsKey(itemStack)) {
			events.get(itemStack).onAction(itemStack);
		}
	}
	
	public void open() {
		player.openInventory(inventory);
		InventoryHandler.addInventory(this);
	}
	
	public void close() {
		player.closeInventory();
		InventoryHandler.removeInventory(this);
	}
	
	public void clear() {
		inventory.clear();
	}
	
	public void setItemOption(ItemOption itemOption) {
		this.itemoption = itemOption;
	}
	
	public ItemOption getItemOption() {
		return itemoption;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
}
