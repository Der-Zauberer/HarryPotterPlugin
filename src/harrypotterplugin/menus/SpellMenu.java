package harrypotterplugin.menus;

import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import harrypotterplugin.utilities.PlayerInventory;
import harrypotterplugin.utilities.Spell;

public class SpellMenu extends PlayerInventory {
	
	public SpellMenu(Player player) {
		super(player, 4, "Spells");
		setFixed(true);
		final List<ItemStack> spells = Spell.getSpells().stream().map(Spell::getItemStack).collect(Collectors.toList());
		setItemList(spells, event -> {}, event -> new MainMenu(player).open());
	}

}
