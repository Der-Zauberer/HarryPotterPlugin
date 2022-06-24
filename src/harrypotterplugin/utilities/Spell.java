package harrypotterplugin.utilities;

import java.util.function.Consumer;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Spell {

    public enum SpellType {TRANSFIGURATION, CHARM, CURSE, COUNTER_CHARM, HEALING}

    private final String name;
    private final SpellType type;
    private final int mana;
    private Consumer<Player> spellCastAction;

    public Spell(String name, SpellType type, int mana) {
        this.name = name;
        this.type = type;
        this.mana = mana;
    }

    public void castSpell(Player player) {
        if (spellCastAction != null) spellCastAction.accept(player);
    }

    public ExtendedItemStack getSpellBook() {
        return new ExtendedItemStack(name, Material.BOOK, 0);
    }
    
    public SpellType getType() {
		return type;
	}
    
    public int getMana() {
		return mana;
	}
    
    public void setSpellCastAction(Consumer<Player> spellCastAction) {
		this.spellCastAction = spellCastAction;
	}
    
    public Consumer<Player> getSpellCastAction() {
		return spellCastAction;
	}

}
