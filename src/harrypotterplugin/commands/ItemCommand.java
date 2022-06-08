package harrypotterplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import harrypotterplugin.utilities.ExtendedItemStack;
import harrypotterplugin.utilities.PlayerInventory;

public class ItemCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			final PlayerInventory inventory = new PlayerInventory((Player) sender, 2, "Harry Potter Items");
			inventory.setFixed(true);
			inventory.setItemList(ExtendedItemStack.getItemsAsItemStack(), event -> event.getWhoClicked().getInventory().addItem(event.getCurrentItem()));
			inventory.open();
		}
		return true;
	}

}
