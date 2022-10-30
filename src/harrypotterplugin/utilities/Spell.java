package harrypotterplugin.utilities;

import java.util.ArrayList;
import java.util.function.Consumer;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Spell {

    private final String name;
    private final String description;
    private final SpellType type;
    private final int mana;
    private final SpellProjectile spellProjectile;
    private final ExtendedItemStack item;
    private Consumer<Player> spellCastAction;
    
    private static final ArrayList<Spell> spells = new ArrayList<>();

    public Spell(String name, String description, SpellType type, int mana, SpellProjectile spellProjectile) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.mana = mana;
        this.spellProjectile = spellProjectile;
        item = new ExtendedItemStack(ChatColor.BOLD + (type.getColor() + name), Material.BOOK);
        item.setLore(ChatColor.DARK_GRAY + type.getDisplayName() + "\n\n" + ChatColor.GRAY + this.description);
    }
    
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
    
    public SpellType getType() {
		return type;
	}
    
    public int getMana() {
		return mana;
	}
    
	public SpellProjectile getSpellProjectile() {
		return spellProjectile;
	}
    
    public void setSpellCastAction(Consumer<Player> spellCastAction) {
		this.spellCastAction = spellCastAction;
	}
    
    public Consumer<Player> getSpellCastAction() {
		return spellCastAction;
	}
    
    public void castSpell(Player player) {
        if (spellCastAction != null) spellCastAction.accept(player);
    }
    
    public ItemStack getItemStack() {
    	return item;
    }
    
    public static void registerSpell(Spell spell) {
    	spells.add(spell);
    }
    
	public static ArrayList<Spell> getSpells() {
		return spells;
	}

}
