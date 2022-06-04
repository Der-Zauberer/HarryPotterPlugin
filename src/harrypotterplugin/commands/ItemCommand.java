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
			PlayerInventory inventory = new PlayerInventory((Player) sender, 1, "Harry Potter Items");
			inventory.setFixed(true);
			int i = 0;
			for(ExtendedItemStack itemStack : ExtendedItemStack.getItems()) {
				inventory.setItem(i, itemStack, event -> event.getWhoClicked().getInventory().addItem(event.getCurrentItem()));
				i++;
			}
			inventory.open();
		}
		return true;
	}

}
