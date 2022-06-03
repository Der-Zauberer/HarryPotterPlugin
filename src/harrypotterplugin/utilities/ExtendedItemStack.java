package harrypotterplugin.utilities;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import harrypotterplugin.actions.ItemInteractAction;
import harrypotterplugin.actions.ItemLeftClickAction;
import harrypotterplugin.actions.ItemRightClickAction;

public class ExtendedItemStack extends ItemStack {
	
	private ItemInteractAction interactAction;
	private ItemLeftClickAction leftClickAction;
	private ItemRightClickAction rightClickAction;
	
	public ExtendedItemStack(String displayName, Material material, int customModelData) {
		super(material);
		ItemMeta itemMeta = getItemMeta();
		itemMeta.setDisplayName(ChatColor.RESET + displayName);
		itemMeta.setCustomModelData(customModelData);
		setItemMeta(itemMeta);
	}
	
	public ExtendedItemStack(String displayName, Material material) {
		super(material);
		ItemMeta itemMeta = getItemMeta();
		itemMeta.setDisplayName(ChatColor.RESET + displayName);
		setItemMeta(itemMeta);
	}
	
	public ExtendedItemStack setDisplayName(String displayName) {
		ItemMeta itemMeta = getItemMeta();
		itemMeta.setDisplayName(ChatColor.RESET + displayName);
		setItemMeta(itemMeta);
		return this;
	}
	
	public ExtendedItemStack setCustomModelData(int customModelData) {
		ItemMeta itemMeta = getItemMeta();
		itemMeta.setCustomModelData(customModelData);
		setItemMeta(itemMeta);
		return this;
	}
	
	public ExtendedItemStack setLore(String string) {
		String[] list = string.split("\n");
		List<String> lore = new ArrayList<>();
		for (String line : list) lore.add(line);
		ItemMeta itemMeta = getItemMeta();
		itemMeta.setLore(lore);
		setItemMeta(itemMeta);
		return this;
	}
	
	public ExtendedItemStack setLore(List<String> lore) {
		ItemMeta itemMeta = getItemMeta();
		itemMeta.setLore(lore);
		setItemMeta(itemMeta);
		return this;
	}
	
	public ExtendedItemStack removeLore() {
		ItemMeta itemMeta = getItemMeta();
		itemMeta.setLore(new ArrayList<String>());
		setItemMeta(itemMeta);
		return this;
	}
	
	public ExtendedItemStack addItemFlag(ItemFlag flag) {
		ItemMeta itemMeta = getItemMeta();
		itemMeta.addItemFlags(flag);
		setItemMeta(itemMeta);
		return this;
	}
	
	public ExtendedItemStack removeItemFlag(ItemFlag flag) {
		ItemMeta itemMeta = getItemMeta();
		itemMeta.removeItemFlags(flag);
		setItemMeta(itemMeta);
		return this;
	}
	
	public ExtendedItemStack addPotionMeta(Color color, PotionEffect effect) {
		if(getType() == Material.POTION) {
			PotionMeta potionMeta = (PotionMeta) getItemMeta();
			potionMeta.setColor(color);
			potionMeta.addCustomEffect(effect, true);
			setItemMeta(potionMeta);
		}
		return this;
	}
	
	public ExtendedItemStack addPlayerSkullMeta(Player player) {
		if(getType() == Material.PLAYER_HEAD) {
			SkullMeta skullMeta = (SkullMeta) getItemMeta();
			skullMeta.setOwningPlayer(player);
			setItemMeta(skullMeta);
		}
		return this;
	}
	
	public void setOnInteract(ItemInteractAction interactAction) {
		this.interactAction = interactAction;
	}
	
	public ItemInteractAction getOnInteract() {
		return interactAction;
	}
	
	public void setOnLeftClick(ItemLeftClickAction leftClickAction) {
		this.leftClickAction = leftClickAction;
	}
	
	public ItemLeftClickAction getOnLeftClick() {
		return leftClickAction;
	}
	
	public void setOnRightClick(ItemRightClickAction rightClickAction) {
		this.rightClickAction = rightClickAction;
	}
	
	public ItemRightClickAction getOnRightClick() {
		return rightClickAction;
	}
	
}
