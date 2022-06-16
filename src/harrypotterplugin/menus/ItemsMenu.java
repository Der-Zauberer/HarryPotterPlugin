package harrypotterplugin.menus;

import org.bukkit.entity.Player;

import harrypotterplugin.utilities.ExtendedItemStack;
import harrypotterplugin.utilities.PlayerInventory;

public class ItemsMenu extends PlayerInventory {

	public ItemsMenu(Player player) {
		super(player, 4, "All Items");
		setFixed(true);
		setItemList(ExtendedItemStack.getItemsAsItemStack(), event -> event.getWhoClicked().getInventory().addItem(event.getCurrentItem()), event -> new MainMenu(player).open());
	}

}
