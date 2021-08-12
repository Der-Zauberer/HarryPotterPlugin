package harrypotterplugin.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import harrypotterplugin.utilities.ItemBuilder;
import harrypotterplugin.utilities.UsableItem;

public class MagicalPickaxeItem extends UsableItem {

	public MagicalPickaxeItem() {
		super(Material.DIAMOND_PICKAXE);
		ItemBuilder itembuilder = new ItemBuilder(ChatColor.DARK_PURPLE + "Magical Pickaxe");
		itembuilder.setCustomModelData(1);
		itembuilder.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
		itembuilder.buildItem(this);
	}

	@Override
	public void onItemUse(Player player, ItemStack itemstack, Action action) {}

}
