package harrypotterplugin.utilities;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public interface UsableItem {
	
	public abstract ItemStack getItem();
	public abstract void onItemUse(Player player, ItemStack itemstack, Action action);

}
