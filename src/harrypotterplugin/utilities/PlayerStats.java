package harrypotterplugin.utilities;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PlayerStats implements Listener {
	
	private final Player player;
	private int mana;
	private int power;
	private int defence;
	
	private static final HashMap<Player, PlayerStats> players = new HashMap<>();
	private static final File file = new File("plugins/HarryPotter", "player_stats.yml");
	private static final FileConfiguration config = YamlConfiguration.loadConfiguration(file);
	private static final ListenerClass listener = new ListenerClass();
	
	private PlayerStats(Player player) {
		this.player = player;
		mana = config.getInt(player.getUniqueId() + ".mana", 100);
		power = config.getInt(player.getUniqueId() + ".power", 100);
		defence = config.getInt(player.getUniqueId() + ".defence", 100);
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
	
	public void setPower(int power) {
		this.power = power;
	}
	
	public int getPower() {
		return power;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getDefence() {
		return defence;
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
	
	public static void reloadAll() {
		ListenerClass.reloadAll();
	}
	
	public static ListenerClass getListener() {
		return listener;
	}
	
	private static class ListenerClass implements Listener {
		
		public static void reloadAll() {
			for (Player player : Bukkit.getOnlinePlayers()) {
				onPlayerQuit(new PlayerQuitEvent(player, null));
				onPlayerJoin(new PlayerJoinEvent(player, null));
			}
		}
		
		@EventHandler
		public static void onPlayerJoin(PlayerJoinEvent event) {
			players.put(event.getPlayer(), new PlayerStats(event.getPlayer()));
		}
		
		@EventHandler
		public static void onPlayerQuit(PlayerQuitEvent event) {
			final PlayerStats stats = getPlayerStats(event.getPlayer());
			if (stats == null) return;
			config.set(stats.getPlayer().getUniqueId() + ".name", stats.getPlayer().getName());
			config.set(stats.getPlayer().getUniqueId() + ".mana", stats.getMana());
			config.set(stats.getPlayer().getUniqueId() + ".power", stats.getPower());
			config.set(stats.getPlayer().getUniqueId() + ".defence", stats.getDefence());
			saveConfig();
			players.remove(stats.getPlayer());
		}
		
	}

}
