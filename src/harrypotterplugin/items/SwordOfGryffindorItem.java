package harrypotterplugin.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import harrypotterplugin.utilities.ExtendedItemStack;

public class SwordOfGryffindorItem extends ExtendedItemStack {

	public SwordOfGryffindorItem() {
		super("Sword of Gryffindor", Material.CARROT_ON_A_STICK, 23);
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY + "A thousand-year-old, goblin-made magical sword");
		lore.add(ChatColor.GRAY + "owned by the famed wizard Godric Gryffindor");
		setLore(lore);
	}
	
}
