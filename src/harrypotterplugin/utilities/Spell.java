package harrypotterplugin.utilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import harrypotterplugin.actions.SpellCastAction;

public class Spell {
	
	public enum SpellType{TRANSFIGURATION, CHARM, CURSE, COUNTER_CHARM, HEALING}
	
	private final String name;
	private final SpellType type;
	private final int mana;
	private SpellCastAction spellCastAction;
	
	public Spell(String name, SpellType type, int mana) {
		this.name = name;
		this.type = type;
		this.mana = mana;
	}
	
	public void cast(Player player) {
		if (spellCastAction != null) spellCastAction.onAction(player);
	}
	
	public ExtendedItemStack getSpellBook() {
		ExtendedItemStack itemStack = new ExtendedItemStack(name, Material.BOOK, 0);
		return itemStack;
	}
	
	public String getName() {
		return name;
	}
	
	public SpellType getType() {
		return type;
	}
	
	public int getMana() {
		return mana;
	}
	
	public void setSpellCastAction(SpellCastAction spellCastAction) {
		this.spellCastAction = spellCastAction;
	}
	
	public SpellCastAction getSpellCastAction() {
		return spellCastAction;
	}

}
