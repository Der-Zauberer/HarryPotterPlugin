package harrypotterplugin.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import harrypotterplugin.utilities.ItemBuilder;
import harrypotterplugin.utilities.UsableItem;

public class WandItem extends UsableItem {
	
	public WandItem() {
		ItemBuilder itembuilder = new ItemBuilder("Wand", Material.CARROT_ON_A_STICK);
		itembuilder.setCustomModelData(1);
		itembuilder.buildItem(this);
	}

	@Override
	public void onItemUse(Player player, ItemStack itemstack, Action action) {
		player.sendMessage("Test");
	}

}
