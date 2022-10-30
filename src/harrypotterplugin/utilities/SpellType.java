package harrypotterplugin.utilities;

import org.bukkit.ChatColor;

public enum SpellType {
	
	CHARM("Charm", ChatColor.BLUE), 
	COUNTER_CHARM("Counter Charm", ChatColor.YELLOW),
	CURSE("Curse", ChatColor.DARK_RED), 
	TRANSFIGURATION("Transfiguration", ChatColor.GREEN),
	HEALING("Headling", ChatColor.RED);
	
	final String displayName;
	final ChatColor color;
	
	private SpellType(String displayName, ChatColor color) {
		this.displayName = displayName;
		this.color = color;
	}

	public ChatColor getColor() {
		return color;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
}
