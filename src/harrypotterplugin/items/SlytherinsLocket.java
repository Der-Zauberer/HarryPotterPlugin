package harrypotterplugin.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import harrypotterplugin.utilities.ExtendedItemStack;

public class SlytherinsLocket extends ExtendedItemStack {

	public SlytherinsLocket() {
		super("Slytherin's Locket", Material.CARROT_ON_A_STICK, 26);
		setLore(ChatColor.GRAY + "A piece of jewellery originally owned by Salazar Slytherin");
	}

}
