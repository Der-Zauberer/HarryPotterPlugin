package harrypotterplugin.menus;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import harrypotterplugin.items.WandItem;
import harrypotterplugin.utilities.ExtendedItemStack;
import harrypotterplugin.utilities.PlayerInventory;

public class MainMenu extends PlayerInventory {

	public MainMenu(Player player) {
		super(player, 4, "Harry Potter Plugin");
		setFixed(true);
		setItem(4, new ExtendedItemStack(ChatColor.RED + "Coming soon!", Material.PLAYER_HEAD).addPlayerSkullMeta(player));
		setItem(19, new WandItem().setDisplayName("Spells"), event -> new SpellMenu(player).open());
		setItem(21, new ExtendedItemStack(ChatColor.RED + "Coming soon!", Material.BARRIER));
		setItem(23, new ExtendedItemStack(ChatColor.RED + "Coming soon!", Material.BARRIER));
		setItem(25, new ExtendedItemStack(ChatColor.RED + "Coming soon!", Material.BARRIER));
		if (player.getGameMode() == GameMode.CREATIVE) setItem(31, new WandItem().setDisplayName("All Items"), event -> new ItemsMenu(player).open());
	}

}
