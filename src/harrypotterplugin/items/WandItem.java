package harrypotterplugin.items;

import org.bukkit.Material;
import org.bukkit.entity.WitherSkull;
import harrypotterplugin.utilities.ExtendedItemStack;

public class WandItem extends ExtendedItemStack {
	
	public WandItem() {
		super("Wand", Material.CARROT_ON_A_STICK, 1);
		setOnLeftClick(event -> {
			WitherSkull projectile = event.getPlayer().launchProjectile(WitherSkull.class);
			projectile.setShooter(event.getPlayer());
			projectile.setVelocity(event.getPlayer().getEyeLocation().getDirection().multiply(2));
			projectile.addScoreboardTag("spell");
		});
	}

}
