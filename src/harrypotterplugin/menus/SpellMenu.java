package harrypotterplugin.menus;

import java.util.ArrayList;
import org.bukkit.entity.Player;
import harrypotterplugin.utilities.PlayerInventory;

public class SpellMenu extends PlayerInventory {
	
	public SpellMenu(Player player) {
		super(player, 4, "Spells");
		setFixed(true);
		setItemList(new ArrayList<>(), event -> {}, event -> new MainMenu(player).open());
	}

}
