package harrypotterplugin.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

import harrypotterplugin.utilities.ItemBuilder;
import harrypotterplugin.utilities.UsableItem;

public class GoldenFeatherItem extends UsableItem {

	public GoldenFeatherItem() {
		super(Material.CARROT_ON_A_STICK);
		ItemBuilder itembuilder = new ItemBuilder("Golden Feather");
		itembuilder.setLore(ChatColor.GRAY + "Protects you from fall damage");
		itembuilder.setCustomModelData(22);
		itembuilder.buildItem(this);
	}

	@Override
	public void onItemUse(Player player, ItemStack itemstack, Action action) {}

}
