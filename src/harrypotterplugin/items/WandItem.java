package harrypotterplugin.items;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import harrypotterplugin.utilities.ExtendedItemStack;
import harrypotterplugin.utilities.SpellProjectile;

public class WandItem extends ExtendedItemStack {
	
	public WandItem() {
		super("Wand", Material.CARROT_ON_A_STICK, 1);
		setLeftClickAction(event -> {
			SpellProjectile spellProjectile = new SpellProjectile(1, 70, event.getPlayer().getEyeLocation(), event.getPlayer().getEyeLocation().getDirection());
			spellProjectile.setHitEntityAction(entity -> {
				if (entity instanceof LivingEntity) {
					((LivingEntity)entity).damage(1000);
				}
			});
			spellProjectile.setOutrangedAction(() -> {
				System.out.println("Test");
			});
			spellProjectile.launch(event.getPlayer());
		});
	}

}
