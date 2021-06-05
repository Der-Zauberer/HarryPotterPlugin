package harrypotterplugin.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import harrypotterplugin.commands.ItemCommand;
import harrypotterplugin.handler.InventoryHandler;
import harrypotterplugin.handler.ItemHandler;
import harrypotterplugin.items.WandItem;

public class HarryPotterPlugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		registerCommands();
		registerEvents();
		registerItem();
	}
	
	private void registerCommands() {
		getCommand("HarryPotterPlugin").setExecutor(new ItemCommand());
	}
	
	private void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new InventoryHandler(), this);
		Bukkit.getPluginManager().registerEvents(new ItemHandler(), this);
	}
	
	private void registerItem() {
		ItemHandler.registerItem(new WandItem());
	}

}