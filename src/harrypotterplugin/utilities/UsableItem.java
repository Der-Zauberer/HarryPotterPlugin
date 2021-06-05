package harrypotterplugin.utilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public abstract class UsableItem extends ItemStack {

	public UsableItem(Material material) {
		super(material);
	}
	
	public abstract void onItemUse(Player player, ItemStack itemstack, Action action);
	
}