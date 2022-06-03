package harrypotterplugin.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import harrypotterplugin.utilities.ExtendedItemStack;

public class MagicalAxeItem extends ExtendedItemStack {

	public MagicalAxeItem() {
		super(ChatColor.DARK_PURPLE + "Magical Axe", Material.DIAMOND_AXE, 1);
		addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
	}

}
