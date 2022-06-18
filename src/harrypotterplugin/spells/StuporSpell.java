package harrypotterplugin.spells;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import harrypotterplugin.utilities.Spell;
import harrypotterplugin.utilities.SpellProjectile;

public class StuporSpell extends Spell {

	public StuporSpell() {
		super("Stupor", SpellType.CHARM, 2);
		setSpellCastAction(event -> {
			SpellProjectile spellProjectile = new SpellProjectile(1, 40, event.getPlayer().getEyeLocation(), event.getPlayer().getEyeLocation().getDirection());
			spellProjectile.setHitEntityAction(entity -> {
				if (entity instanceof LivingEntity) {
					((LivingEntity) entity).damage(4);
					LivingEntity livingEntity = ((LivingEntity) entity);
					EntityDamageByEntityEvent damageEvent = new EntityDamageByEntityEvent(event.getPlayer(), livingEntity, DamageCause.MAGIC, 4);
					livingEntity.setLastDamageCause(damageEvent);
					Bukkit.getPluginManager().callEvent(damageEvent);
					if (!damageEvent.isCancelled() && damageEvent.getEntity() instanceof LivingEntity) ((LivingEntity) damageEvent.getEntity()).damage(damageEvent.getDamage(), damageEvent.getDamager());
				}
			});
			spellProjectile.launch();
		});
	}

}
