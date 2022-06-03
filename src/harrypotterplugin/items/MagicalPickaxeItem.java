package harrypotterplugin.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import harrypotterplugin.utilities.ExtendedItemStack;

public class MagicalPickaxeItem extends ExtendedItemStack {

	public MagicalPickaxeItem() {
		super(ChatColor.DARK_PURPLE + "Magical Pickaxe", Material.DIAMOND_PICKAXE, 1);
		addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
	}

}
