package harrypotterplugin.utilities;

import org.bukkit.ChatColor;

public enum SpellType {
	
	CHARM(ChatColor.BLUE), 
	COUNTER_CHARM(ChatColor.YELLOW),
	CURSE(ChatColor.DARK_RED), 
	TRANSFIGURATION(ChatColor.GREEN),
	HEALING(ChatColor.RED);
	
	final ChatColor color;
	
	private SpellType(ChatColor color) {
		this.color = color;
	}

	public ChatColor getColor() {
		return color;
	}
	
}
