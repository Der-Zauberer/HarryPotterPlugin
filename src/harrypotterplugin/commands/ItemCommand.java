package harrypotterplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import harrypotterplugin.utilities.PlayerInventory;
import harrypotterplugin.utilities.PlayerInventory.ItemOption;

public class ItemCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			PlayerInventory inventory = new PlayerInventory((Player) sender, 9, "Harry Potter Items");
			inventory.setItemOption(ItemOption.GETABLE);
			inventory.open();
		}
		return true;
	}

}
