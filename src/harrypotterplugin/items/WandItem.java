package harrypotterplugin.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import harrypotterplugin.utilities.ItemBuilder;
import harrypotterplugin.utilities.UsableItem;

public class WandItem extends UsableItem {
	
	public WandItem() {
		super(Material.CARROT_ON_A_STICK);
		ItemBuilder itembuilder = new ItemBuilder("Wand");
		itembuilder.setCustomModelData(1);
		itembuilder.buildItem(this);
	}

	@Override
	public void onItemUse(Player player, ItemStack itemstack, Action action) {
		if(action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
			WitherSkull projectile = player.launchProjectile(WitherSkull.class);
			projectile.setShooter(player);
			projectile.setVelocity(player.getEyeLocation().getDirection().multiply(2));
			projectile.addScoreboardTag("spell");
		}
	}

}
