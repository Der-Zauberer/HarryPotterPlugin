package harrypotterplugin.main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import harrypotterplugin.commands.ItemCommand;
import harrypotterplugin.events.HarryPotterPluginListener;
import harrypotterplugin.handler.ItemHandler;
import harrypotterplugin.items.BroomstickItem;
import harrypotterplugin.items.WandItem;
import harrypotterplugin.items.BroomstickItem.BroomstickType;
import harrypotterplugin.utilities.PlayerInventory;
import harrypotterplugin.items.GoldenFeatherItem;
import harrypotterplugin.items.MagicalAxeItem;
import harrypotterplugin.items.MagicalPickaxeItem;
import harrypotterplugin.items.TimeTurnerItem;

public class HarryPotterPlugin extends JavaPlugin {
	
	public static HarryPotterPlugin instance;
	
	@Override
	public void onEnable() {
		setInstance(this);
		registerCommands();
		registerEvents();
		registerItem();
		registerCraftingRecipes();
	}

	private static void registerCommands() {
		instance.getCommand("HarryPotterPlugin").setExecutor(new ItemCommand());
	}
	
	private void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new HarryPotterPluginListener(), instance);
		Bukkit.getPluginManager().registerEvents(PlayerInventory.getInstance(), instance);
		Bukkit.getPluginManager().registerEvents(new ItemHandler(), instance);
	}
	
	private static void registerItem() {
		ItemHandler.registerItem(new WandItem());
		ItemHandler.registerItem(new BroomstickItem(BroomstickType.SHOOTING_STAR));
		ItemHandler.registerItem(new BroomstickItem(BroomstickType.NIMBUS_2000));
		ItemHandler.registerItem(new BroomstickItem(BroomstickType.NIMBUS_2001));
		ItemHandler.registerItem(new BroomstickItem(BroomstickType.FIREBOLD));
		ItemHandler.registerItem(new MagicalPickaxeItem());
		ItemHandler.registerItem(new MagicalAxeItem());
		ItemHandler.registerItem(new TimeTurnerItem());
		ItemHandler.registerItem(new GoldenFeatherItem());
	}
	
	private static void registerCraftingRecipes() {
		Bukkit.getServer().addRecipe(new ShapedRecipe(createNamespacedKey("shooting_star"), new BroomstickItem(BroomstickType.SHOOTING_STAR)).shape("  S", "HS ", "HH ").setIngredient('S', Material.STICK).setIngredient('H', Material.HAY_BLOCK));
		Bukkit.getServer().addRecipe(new ShapedRecipe(createNamespacedKey("nimbus_2000"), new BroomstickItem(BroomstickType.NIMBUS_2000)).shape("  S", "BS ", "BB ").setIngredient('S', Material.STICK).setIngredient('B', Material.BLAZE_ROD));
		Bukkit.getServer().addRecipe(new ShapedRecipe(createNamespacedKey("nimbus_2001"), new BroomstickItem(BroomstickType.NIMBUS_2000)).shape("  S", "ES ", "WE ").setIngredient('S', Material.BLAZE_ROD).setIngredient('E', Material.ENDER_EYE).setIngredient('W', Material.WITHER_SKELETON_SKULL));
		Bukkit.getServer().addRecipe(new ShapedRecipe(createNamespacedKey("firebold"), new BroomstickItem(BroomstickType.FIREBOLD)).shape("  S", "WS ", "WW ").setIngredient('S', Material.END_ROD).setIngredient('W', Material.NETHER_STAR));
		Bukkit.getServer().addRecipe(new ShapedRecipe(createNamespacedKey("magical_pickaxe"), new MagicalPickaxeItem()).shape("AAA", " S ", " S ").setIngredient('S', Material.STICK).setIngredient('A', Material.AMETHYST_SHARD));
		Bukkit.getServer().addRecipe(new ShapedRecipe(createNamespacedKey("magical_axe"), new MagicalAxeItem()).shape(" AA", " SA", " S ").setIngredient('S', Material.STICK).setIngredient('A', Material.AMETHYST_SHARD));
		Bukkit.getServer().addRecipe(new ShapedRecipe(createNamespacedKey("time_turner"), new TimeTurnerItem()).shape(" G ", "BCB", " B ").setIngredient('G', Material.GOLD_INGOT).setIngredient('B', Material.GOLD_BLOCK).setIngredient('C', Material.CLOCK));
		Bukkit.getServer().addRecipe(new ShapedRecipe(createNamespacedKey("golden_feather"), new GoldenFeatherItem()).shape("GGG", "GFG", "GGG").setIngredient('G', Material.GOLD_INGOT).setIngredient('F', Material.FEATHER));
	}
	
	public static NamespacedKey createNamespacedKey(String string) {
		NamespacedKey namespacedKey = new NamespacedKey(instance, string);
		ItemHandler.registerNameSpaceKey(namespacedKey);
		return namespacedKey;
	}
	
	public static HarryPotterPlugin getInstance() {
		return instance;
	}
	
	public static void setInstance(HarryPotterPlugin instance) {
		HarryPotterPlugin.instance = instance;
	}

}