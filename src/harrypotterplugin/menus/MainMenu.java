package harrypotterplugin.menus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import harrypotterplugin.items.WandItem;
import harrypotterplugin.utilities.ExtendedItemStack;
import harrypotterplugin.utilities.PlayerInventory;
import harrypotterplugin.utilities.PlayerStats;

public class MainMenu extends PlayerInventory {

	public MainMenu(Player player) {
		super(player, 4, "Harry Potter Plugin");
		setFixed(true);
		setItem(4, getStatsItem(player));
		setItem(19, new WandItem().setDisplayName("Spells"), event -> new SpellMenu(player).open());
		setItem(21, new ExtendedItemStack(ChatColor.RED + "Coming soon!", Material.BARRIER));
		setItem(23, new ExtendedItemStack(ChatColor.RED + "Coming soon!", Material.BARRIER));
		setItem(25, new ExtendedItemStack(ChatColor.RED + "Coming soon!", Material.BARRIER));
		if (player.getGameMode() == GameMode.CREATIVE) setItem(31, new WandItem().setDisplayName("All Items"), event -> new ItemsMenu(player).open());
	}
	
	private ExtendedItemStack getStatsItem(Player player) {
		final PlayerStats stats = PlayerStats.getPlayerStats(player);
		final List<String> string = new ArrayList<>();
		string.add("");
		string.add(ChatColor.RESET + (ChatColor.AQUA + "Mana: ") + ChatColor.BOLD + stats.getMana());
		string.add(ChatColor.RESET + (ChatColor.RED + "Power: ") + ChatColor.BOLD + stats.getPower());
		string.add(ChatColor.RESET + (ChatColor.BLUE + "Defence: ") + ChatColor.BOLD + stats.getDefence());
		string.add("");
		string.add(ChatColor.RESET + (ChatColor.DARK_GRAY + "Stats are only valid for spells!"));
		
		final ExtendedItemStack statsItem = new ExtendedItemStack(ChatColor.BOLD + player.getName(), Material.PLAYER_HEAD);
		statsItem.addPlayerSkullMeta(player);
		statsItem.setLore(string);
		return statsItem;
	}

}
