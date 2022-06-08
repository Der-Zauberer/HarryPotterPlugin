package harrypotterplugin.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import harrypotterplugin.utilities.ExtendedItemStack;

public class RavenclawsDiadem extends ExtendedItemStack {

	public RavenclawsDiadem() {
		super("Ravenclaws Diadem", Material.CARROT_ON_A_STICK, 25);
		setLore(ChatColor.GRAY + "The only known relic once belonging to Rowena Ravenclaw");
	}
	
}
