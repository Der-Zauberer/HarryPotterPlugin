package harrypotterplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import harrypotterplugin.handler.ItemHandler;
import harrypotterplugin.utilities.PlayerInventory;
import harrypotterplugin.utilities.UsableItem;
import harrypotterplugin.utilities.PlayerInventory.ItemOption;

public class ItemCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			PlayerInventory inventory = new PlayerInventory((Player) sender, 18, "Harry Potter Items");
			inventory.setItemOption(ItemOption.GETABLE);
			int i = 0;
			for(UsableItem usableitem : ItemHandler.getUsableitems()) {
				inventory.setItem(i, usableitem);
				i++;
			}
			inventory.open();
		}
		return true;
	}

}
