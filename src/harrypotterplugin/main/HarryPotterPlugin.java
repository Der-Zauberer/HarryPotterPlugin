package harrypotterplugin.main;

import org.bukkit.plugin.java.JavaPlugin;

import harrypotterplugin.commands.ItemCommand;

public class HarryPotterPlugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		registerCommands();
	}
	
	private void registerCommands() {
		getCommand("HarryPotterPlugin").setExecutor(new ItemCommand());
	}

}