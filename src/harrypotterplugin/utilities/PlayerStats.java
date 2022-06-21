package harrypotterplugin.utilities;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PlayerStats {
	
	private final Player player;
	private int mana;
	
	private static final HashMap<Player, PlayerStats> players = new HashMap<>();
	private static final File file = new File("plugins/HarryPotter", "player_stats.yml");
	public static final FileConfiguration config = YamlConfiguration.loadConfiguration(file);
	
	public PlayerStats(Player player) {
		this.player = player;
		mana = 0;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setMana(int mana) {
		this.mana = mana;
	}
	
	public int getMana() {
		return mana;
	}
	
	public static void join(Player player) {
		players.put(player, null);
	}
	
	public static void leave(Player player) {
		players.remove(player);
	}
	
	public static PlayerStats getPlayerStats(Player player) {
		return players.get(player);
	}
	
	public static void saveConfig() {
		try {
			config.save(file);
		} catch (IOException exception) {
			Bukkit.getLogger().warning("Something went wrong while saving plugins/HarryPotter/player_stats.yml!");
		}
	}

}
