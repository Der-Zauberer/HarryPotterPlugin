package harrypotterplugin.handler;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class ChatHandler implements Listener {
	
	private static ChatColor messagecolor = ChatColor.WHITE;
	private static ChatColor errorcolor = ChatColor.RED;
	private static String servername = "derzauberer.eu";
	
	public static enum ErrorMessage{ONLYCONSOLE, ONLYPLAYER, NOPERMISSION, PLAYERNOTONLINE, WORLDDOESNOTEXIST, NOTENOUGHTARGUMENTS}
	
	public static void sendServerMessage(Player player, String message) {
		player.sendMessage(servername + messagecolor + " " + message);
	}
	
	public static void sendServerMessage(CommandSender sender, String message) {
		sender.sendMessage(servername + messagecolor + " " + message);
	}
	
	public static void sendServerErrorMessage(Player player, String message) {
		player.sendMessage(servername + errorcolor + " " + message);
	}
	
	public static void sendServerErrorMessage(CommandSender sender, String message) {
		sender.sendMessage(servername + errorcolor + " " + message);
	}
	
	public static void sendServerErrorMessage(Player player, ErrorMessage errormessage) {
		switch (errormessage) {
		case ONLYCONSOLE: player.sendMessage(servername + errorcolor + " " + " This command can only be used by the console!"); break;
		case ONLYPLAYER: player.sendMessage(servername + errorcolor + " " + " This command can only be used by players!"); break;
		case NOTENOUGHTARGUMENTS: player.sendMessage(servername + errorcolor + " Not enought arguments!"); break;
		case PLAYERNOTONLINE: player.sendMessage(servername + errorcolor + " The player ist not online!"); break;
		case WORLDDOESNOTEXIST: player.sendMessage(servername + errorcolor + " The world does not exist!"); break;
		case NOPERMISSION: player.sendMessage(servername + errorcolor + " " + " You have no permission to do that!"); break;
		default: break;
		}
	}
	
	public static void sendServerErrorMessage(CommandSender sender, ErrorMessage errormessage) {
		switch (errormessage) {
		case ONLYCONSOLE: sender.sendMessage(servername + errorcolor + " " + " This command can only be used by the console!"); break;
		case ONLYPLAYER: sender.sendMessage(servername + errorcolor + " " + " This command can only be used by players!"); break;
		case NOTENOUGHTARGUMENTS: sender.sendMessage(servername + errorcolor + " Not enought arguments!"); break;
		case PLAYERNOTONLINE: sender.sendMessage(servername + errorcolor + " The player ist not online!"); break;
		case WORLDDOESNOTEXIST: sender.sendMessage(servername + errorcolor + " The world does not exist!"); break;
		case NOPERMISSION: sender.sendMessage(servername + errorcolor + " " + " You have no permission to do that!"); break;
		default: break;
		}
	}
	
	public static ChatColor parseColor(String color) {
		switch (color) {
		case "aqua": return ChatColor.AQUA;
		case "black": return ChatColor.BLACK;
		case "blue": return ChatColor.BLUE;
		case "dark_aqua": return ChatColor.DARK_AQUA;
		case "dark_blue": return ChatColor.DARK_BLUE;
		case "dark_gray": return ChatColor.DARK_GRAY;
		case "dark_green": return ChatColor.DARK_GREEN;
		case "dark_purple": return ChatColor.DARK_PURPLE;
		case "dark_red": return ChatColor.DARK_RED;
		case "gold": return ChatColor.GOLD;
		case "gray": return ChatColor.GRAY;
		case "green": return ChatColor.GREEN;
		case "light_purple": return ChatColor.LIGHT_PURPLE;
		case "red": return ChatColor.RED;
		case "white": return ChatColor.WHITE;
		case "yellow": return ChatColor.YELLOW;
		default: return ChatColor.WHITE;
		}
	}

}
