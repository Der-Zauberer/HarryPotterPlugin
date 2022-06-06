package harrypotterplugin.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import harrypotterplugin.utilities.ExtendedItemStack;

public class GoldenFeatherItem extends ExtendedItemStack {

	public GoldenFeatherItem() {
		super("Golden Feather", Material.CARROT_ON_A_STICK, 22);
		setLore(ChatColor.GRAY + "Protects you from fall damage with the power of \"arresto momentum\"");
	}

}
