package harrypotterplugin.utilities;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import harrypotterplugin.actions.ItemInteractAction;
import harrypotterplugin.main.HarryPotterPlugin;

public class ExtendedItemStack extends ItemStack {
	
	private ItemInteractAction interactAction;
	private ItemInteractAction leftClickAction;
	private ItemInteractAction rightClickAction;
	
	private static final ListenerClass listener = new ListenerClass();
	private static final ArrayList<NamespacedKey> nameSpacedKeys = new ArrayList<>();
	private static final ArrayList<ExtendedItemStack> extendedItemStacks = new ArrayList<>();
	
	public ExtendedItemStack(String displayName, Material material, int customModelData) {
		super(material);
		final ItemMeta itemMeta = getItemMeta();
		itemMeta.setDisplayName(ChatColor.RESET + displayName);
		itemMeta.setCustomModelData(customModelData);
		setItemMeta(itemMeta);
	}
	
	public ExtendedItemStack(String displayName, Material material) {
		super(material);
		final ItemMeta itemMeta = getItemMeta();
		itemMeta.setDisplayName(ChatColor.RESET + displayName);
		setItemMeta(itemMeta);
	}
	
	public ExtendedItemStack setDisplayName(String displayName) {
		final ItemMeta itemMeta = getItemMeta();
		itemMeta.setDisplayName(ChatColor.RESET + displayName);
		setItemMeta(itemMeta);
		return this;
	}
	
	public ExtendedItemStack setCustomModelData(int customModelData) {
		final ItemMeta itemMeta = getItemMeta();
		itemMeta.setCustomModelData(customModelData);
		setItemMeta(itemMeta);
		return this;
	}
	
	public ExtendedItemStack setLore(String string) {
		final String[] list = string.split("\n");
		final List<String> lore = new ArrayList<>();
		for (String line : list) lore.add(line);
		final ItemMeta itemMeta = getItemMeta();
		itemMeta.setLore(lore);
		setItemMeta(itemMeta);
		return this;
	}
	
	public ExtendedItemStack setLore(List<String> lore) {
		final ItemMeta itemMeta = getItemMeta();
		itemMeta.setLore(lore);
		setItemMeta(itemMeta);
		return this;
	}
	
	public ExtendedItemStack removeLore() {
		final ItemMeta itemMeta = getItemMeta();
		itemMeta.setLore(new ArrayList<String>());
		setItemMeta(itemMeta);
		return this;
	}
	
	public ExtendedItemStack addItemFlag(ItemFlag flag) {
		final ItemMeta itemMeta = getItemMeta();
		itemMeta.addItemFlags(flag);
		setItemMeta(itemMeta);
		return this;
	}
	
	public ExtendedItemStack removeItemFlag(ItemFlag flag) {
		final ItemMeta itemMeta = getItemMeta();
		itemMeta.removeItemFlags(flag);
		setItemMeta(itemMeta);
		return this;
	}
	
	public ExtendedItemStack addPotionMeta(Color color, PotionEffect effect) {
		if(getType() == Material.POTION) {
			final PotionMeta potionMeta = (PotionMeta) getItemMeta();
			potionMeta.setColor(color);
			potionMeta.addCustomEffect(effect, true);
			setItemMeta(potionMeta);
		}
		return this;
	}
	
	public ExtendedItemStack addPlayerSkullMeta(Player player) {
		if(getType() == Material.PLAYER_HEAD) {
			final SkullMeta skullMeta = (SkullMeta) getItemMeta();
			skullMeta.setOwningPlayer(player);
			setItemMeta(skullMeta);
		}
		return this;
	}
	
	public void registerEvents() {
		if (this instanceof Listener) Bukkit.getPluginManager().registerEvents((Listener) this, HarryPotterPlugin.getInstance());
	}
	
	public void setInteractAction(ItemInteractAction interactAction) {
		this.interactAction = interactAction;
	}
	
	public ItemInteractAction getInteractAction() {
		return interactAction;
	}
	
	public void setLeftClickAction(ItemInteractAction leftClickAction) {
		this.leftClickAction = leftClickAction;
	}
	
	public ItemInteractAction getLeftClickAction() {
		return leftClickAction;
	}
	
	public void setRightClickAction(ItemInteractAction rightClickAction) {
		this.rightClickAction = rightClickAction;
	}
	
	public ItemInteractAction getRightClickAction() {
		return rightClickAction;
	}
	
	public static void registerItem(ExtendedItemStack itemStack) {
		extendedItemStacks.add(itemStack);
	}
	
	public static ArrayList<ExtendedItemStack> getItems() {
		return extendedItemStacks;
	}
	
	public static ArrayList<ItemStack> getItemsAsItemStack() {
		ArrayList<ItemStack> itemStacks = new ArrayList<>();
		extendedItemStacks.forEach(item -> itemStacks.add(item));
		return itemStacks;
	}
	
	public static void registerNameSpaceKey(NamespacedKey nameSpacedKey) {
		nameSpacedKeys.add(nameSpacedKey);
	}
	
	public static ArrayList<NamespacedKey> getNamespacedKeys() {
		return nameSpacedKeys;
	}
	
	public static ExtendedItemStack getItem(ItemStack itemStack) {
		for (ExtendedItemStack item : extendedItemStacks) {
			if (itemStack != null && item.getType() == itemStack.getType() && itemStack.hasItemMeta() && itemStack.getItemMeta().hasCustomModelData() && item.getItemMeta().getCustomModelData() == itemStack.getItemMeta().getCustomModelData()) {
				return item;
			}
		}
		return null;
	}
	
	public static boolean isItem(ItemStack itemStack, Material material, int customModelData) {
		return itemStack != null && itemStack.getType() == material && itemStack.hasItemMeta() && itemStack.getItemMeta().hasCustomModelData() && itemStack.getItemMeta().getCustomModelData() == customModelData;
	}
	
	public static ListenerClass getListener() {
		return listener;
	}
	
	private static class ListenerClass implements Listener {
		
		@EventHandler
		public static void onPlayerJoin(PlayerJoinEvent event) {
			event.getPlayer().discoverRecipes(ExtendedItemStack.getNamespacedKeys());
		}
		
		@EventHandler
		public static void onEntityTarget(EntityTargetEvent event) {
			if(event.getEntityType() == EntityType.PIG && event.getTarget() instanceof Player) {
				ItemStack mainItem = ((Player)event.getTarget()).getInventory().getItemInMainHand();
				ItemStack secondaryItem = ((Player)event.getTarget()).getInventory().getItemInOffHand();
				if(mainItem.getType() == Material.CARROT_ON_A_STICK && mainItem.hasItemMeta() && mainItem.getItemMeta().hasCustomModelData() && mainItem.getItemMeta().getCustomModelData() != 0) {
					event.setCancelled(true);
				} else if(secondaryItem.getType() == Material.CARROT_ON_A_STICK && secondaryItem.hasItemMeta() && secondaryItem.getItemMeta().hasCustomModelData() && secondaryItem.getItemMeta().getCustomModelData() != 0) {
					event.setCancelled(true);
				}
			}
		}
		
		@EventHandler
		public static void onPlayerInteract(PlayerInteractEvent event) {
			final ExtendedItemStack itemStack;																																						
			if ((itemStack = getItem(event.getItem())) != null) {
				if (itemStack.getInteractAction() != null) itemStack.getInteractAction().onAction(event);
				if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
					if (itemStack.getLeftClickAction() != null) itemStack.getLeftClickAction().onAction(event);
				} else if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if (itemStack.getRightClickAction() != null) itemStack.getRightClickAction().onAction(event);
				}
			}
		}
		
	}
	
}
