package harrypotterplugin.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import harrypotterplugin.utilities.ExtendedItem;

public class MagicalAxeItem extends ExtendedItem {

	public MagicalAxeItem() {
		super(ChatColor.DARK_PURPLE + "Magical Axe", Material.DIAMOND_AXE, 1);
		addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
	}

}
