package harrypotterplugin.main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import harrypotterplugin.commands.HarryPotterCommand;
import harrypotterplugin.items.BroomStickItem;
import harrypotterplugin.items.BroomStickItem.BroomstickType;
import harrypotterplugin.items.GoldenFeatherItem;
import harrypotterplugin.items.HufflepuffsCupItem;
import harrypotterplugin.items.MagicalAxeItem;
import harrypotterplugin.items.MagicalPickaxeItem;
import harrypotterplugin.items.RavenclawsDiadem;
import harrypotterplugin.items.SlytherinsLocket;
import harrypotterplugin.items.SwordOfGryffindorItem;
import harrypotterplugin.items.TimeTurnerItem;
import harrypotterplugin.items.WandItem;
import harrypotterplugin.spells.StuporSpell;
import harrypotterplugin.utilities.ExtendedItemStack;
import harrypotterplugin.utilities.PlayerInventory;
import harrypotterplugin.utilities.PlayerStats;
import harrypotterplugin.utilities.Spell;

public class HarryPotterPlugin extends JavaPlugin {

    public static HarryPotterPlugin instance;

    @Override
    public void onEnable() {
        setInstance(this);
        registerCommands();
        registerEvents();
        registerItem();
        registerSpells();
        registerCraftingRecipes();
        PlayerStats.reloadAll();
    }

    private static void registerCommands() {
    	instance.getCommand("HarryPotter").setExecutor(new HarryPotterCommand());
    }

    private static void registerEvents() {
        Bukkit.getPluginManager().registerEvents(PlayerInventory.getListener(), instance);
        Bukkit.getPluginManager().registerEvents(ExtendedItemStack.getListener(), instance);
        Bukkit.getPluginManager().registerEvents(PlayerStats.getListener(), instance);
    }

    private static void registerItem() {
        ExtendedItemStack.registerItem(new WandItem());
        ExtendedItemStack.registerItem(new BroomStickItem(BroomstickType.SHOOTING_STAR));
        ExtendedItemStack.registerItem(new BroomStickItem(BroomstickType.NIMBUS_2000));
        ExtendedItemStack.registerItem(new BroomStickItem(BroomstickType.NIMBUS_2001));
        ExtendedItemStack.registerItem(new BroomStickItem(BroomstickType.FIREBOLD));
        ExtendedItemStack.registerItem(new MagicalPickaxeItem());
        ExtendedItemStack.registerItem(new MagicalAxeItem());
        ExtendedItemStack.registerItem(new TimeTurnerItem());
        ExtendedItemStack.registerItem(new GoldenFeatherItem());
        ExtendedItemStack.registerItem(new SwordOfGryffindorItem());
        ExtendedItemStack.registerItem(new HufflepuffsCupItem());
        ExtendedItemStack.registerItem(new RavenclawsDiadem());
        ExtendedItemStack.registerItem(new SlytherinsLocket());
    }
    
    private static void registerSpells() {
    	Spell.registerSpell(new StuporSpell());
    }

    private static void registerCraftingRecipes() {
        Bukkit.getServer().addRecipe(new ShapedRecipe(createNamespacedKey("shooting_star"), new BroomStickItem(BroomstickType.SHOOTING_STAR)).shape("  S", "HS ", "HH ").setIngredient('S', Material.STICK).setIngredient('H', Material.HAY_BLOCK));
        Bukkit.getServer().addRecipe(new ShapedRecipe(createNamespacedKey("nimbus_2000"), new BroomStickItem(BroomstickType.NIMBUS_2000)).shape("  S", "BS ", "BB ").setIngredient('S', Material.STICK).setIngredient('B', Material.BLAZE_ROD));
        Bukkit.getServer().addRecipe(new ShapedRecipe(createNamespacedKey("nimbus_2001"), new BroomStickItem(BroomstickType.NIMBUS_2000)).shape("  S", "ES ", "WE ").setIngredient('S', Material.BLAZE_ROD).setIngredient('E', Material.ENDER_EYE).setIngredient('W', Material.WITHER_SKELETON_SKULL));
        Bukkit.getServer().addRecipe(new ShapedRecipe(createNamespacedKey("firebold"), new BroomStickItem(BroomstickType.FIREBOLD)).shape("  S", "WS ", "WW ").setIngredient('S', Material.END_ROD).setIngredient('W', Material.NETHER_STAR));
        Bukkit.getServer().addRecipe(new ShapedRecipe(createNamespacedKey("magical_pickaxe"), new MagicalPickaxeItem()).shape("AAA", " S ", " S ").setIngredient('S', Material.STICK).setIngredient('A', Material.AMETHYST_SHARD));
        Bukkit.getServer().addRecipe(new ShapedRecipe(createNamespacedKey("magical_axe"), new MagicalAxeItem()).shape(" AA", " SA", " S ").setIngredient('S', Material.STICK).setIngredient('A', Material.AMETHYST_SHARD));
        Bukkit.getServer().addRecipe(new ShapedRecipe(createNamespacedKey("time_turner"), new TimeTurnerItem()).shape(" G ", "BCB", " B ").setIngredient('G', Material.GOLD_INGOT).setIngredient('B', Material.GOLD_BLOCK).setIngredient('C', Material.CLOCK));
        Bukkit.getServer().addRecipe(new ShapedRecipe(createNamespacedKey("golden_feather"), new GoldenFeatherItem()).shape("GGG", "GFG", "GGG").setIngredient('G', Material.GOLD_INGOT).setIngredient('F', Material.FEATHER));
    }

    public static NamespacedKey createNamespacedKey(String string) {
        NamespacedKey namespacedKey = new NamespacedKey(instance, string);
        ExtendedItemStack.registerNameSpaceKey(namespacedKey);
        return namespacedKey;
    }

    public static HarryPotterPlugin getInstance() {
        return instance;
    }

    public static void setInstance(HarryPotterPlugin instance) {
        HarryPotterPlugin.instance = instance;
    }

}