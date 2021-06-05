package harrypotterplugin.utilities;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public abstract class UsableItem extends ItemStack {

	public abstract void onItemUse(Player player, ItemStack itemstack, Action action);
	
}