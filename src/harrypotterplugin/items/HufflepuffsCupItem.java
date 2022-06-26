package harrypotterplugin.items;

import org.bukkit.Material;

import harrypotterplugin.utilities.ExtendedItemStack;

public class HufflepuffsCupItem extends ExtendedItemStack {

	public HufflepuffsCupItem() {
		super("Hufflepuff's Cup", Material.CARROT_ON_A_STICK, 24);
		setLore("A magical item created by Helga Hufflepuff");
	}

}
