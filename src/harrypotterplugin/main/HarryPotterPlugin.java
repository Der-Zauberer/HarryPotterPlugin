package harrypotterplugin.main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import harrypotterplugin.commands.ItemCommand;
import harrypotterplugin.events.HarryPotterPluginListener;
import harrypotterplugin.handler.InventoryHandler;
import harrypotterplugin.handler.ItemHandler;
import harrypotterplugin.items.BroomstickItem;
import harrypotterplugin.items.WandItem;
import harrypotterplugin.items.BroomstickItem.BroomstickType;
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

	private void registerCommands() {
		getCommand("HarryPotterPlugin").setExecutor(new ItemCommand());
	}
	
	private void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new HarryPotterPluginListener(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryHandler(), this);
		Bukkit.getPluginManager().registerEvents(new ItemHandler(), this);
	}
	
	private void registerItem() {
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
	
	private void registerCraftingRecipes() {
		NamespacedKey shootingstarrecipe = new NamespacedKey(this, "shooting_star");
		NamespacedKey nimbus2000recipe = new NamespacedKey(this, "nimbus_2000");
		NamespacedKey nimbus2001recipe = new NamespacedKey(this, "nimbus_2001");
		NamespacedKey fireboldrecipe = new NamespacedKey(this, "firebold");
		NamespacedKey magicalpickaxerecipe = new NamespacedKey(this, "magical_pickaxe");
		NamespacedKey magicalaxerecipe = new NamespacedKey(this, "magical_axe");
		NamespacedKey timeturnerrecipe = new NamespacedKey(this, "time_turner");
		NamespacedKey goldenfeatherrecipe = new NamespacedKey(this, "golden_feather");
		
		Bukkit.getServer().addRecipe(new ShapedRecipe(shootingstarrecipe, new BroomstickItem(BroomstickType.SHOOTING_STAR)).shape("  S", "HS ", "HH ").setIngredient('S', Material.STICK).setIngredient('H', Material.HAY_BLOCK));
		Bukkit.getServer().addRecipe(new ShapedRecipe(nimbus2000recipe, new BroomstickItem(BroomstickType.NIMBUS_2000)).shape("  S", "BS ", "BB ").setIngredient('S', Material.STICK).setIngredient('B', Material.BLAZE_ROD));
		Bukkit.getServer().addRecipe(new ShapedRecipe(nimbus2001recipe, new BroomstickItem(BroomstickType.NIMBUS_2000)).shape("  S", "ES ", "WE ").setIngredient('S', Material.BLAZE_ROD).setIngredient('E', Material.ENDER_EYE).setIngredient('W', Material.WITHER_SKELETON_SKULL));
		Bukkit.getServer().addRecipe(new ShapedRecipe(fireboldrecipe, new BroomstickItem(BroomstickType.FIREBOLD)).shape("  S", "WS ", "WW ").setIngredient('S', Material.END_ROD).setIngredient('W', Material.NETHER_STAR));
		Bukkit.getServer().addRecipe(new ShapedRecipe(magicalpickaxerecipe, new MagicalPickaxeItem()).shape("AAA", " S ", " S ").setIngredient('S', Material.STICK).setIngredient('A', Material.AMETHYST_SHARD));
		Bukkit.getServer().addRecipe(new ShapedRecipe(magicalaxerecipe, new MagicalAxeItem()).shape(" AA", " SA", " S ").setIngredient('S', Material.STICK).setIngredient('A', Material.AMETHYST_SHARD));
		Bukkit.getServer().addRecipe(new ShapedRecipe(timeturnerrecipe, new TimeTurnerItem()).shape(" G ", "BCB", " B ").setIngredient('G', Material.GOLD_INGOT).setIngredient('B', Material.GOLD_BLOCK).setIngredient('C', Material.CLOCK));
		Bukkit.getServer().addRecipe(new ShapedRecipe(goldenfeatherrecipe, new GoldenFeatherItem()).shape("GGG", "GFG", "GGG").setIngredient('G', Material.GOLD_INGOT).setIngredient('F', Material.FEATHER));
	
		ItemHandler.registerNameSpaceKey(shootingstarrecipe);
		ItemHandler.registerNameSpaceKey(nimbus2000recipe);
		ItemHandler.registerNameSpaceKey(nimbus2001recipe);
		ItemHandler.registerNameSpaceKey(fireboldrecipe);
		ItemHandler.registerNameSpaceKey(magicalpickaxerecipe);
		ItemHandler.registerNameSpaceKey(magicalaxerecipe);
		ItemHandler.registerNameSpaceKey(timeturnerrecipe);
		ItemHandler.registerNameSpaceKey(goldenfeatherrecipe);
	}
	
	public static HarryPotterPlugin getInstance() {
		return instance;
	}
	
	public static void setInstance(HarryPotterPlugin instance) {
		HarryPotterPlugin.instance = instance;
	}

}