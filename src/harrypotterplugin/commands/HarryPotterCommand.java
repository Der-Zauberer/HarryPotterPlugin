package harrypotterplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import harrypotterplugin.menus.MainMenu;

public class HarryPotterCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) new MainMenu((Player) sender).open();
		return true;
	}

}
