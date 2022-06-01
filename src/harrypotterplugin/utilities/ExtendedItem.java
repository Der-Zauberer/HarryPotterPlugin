package harrypotterplugin.utilities;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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

public class ExtendedItem extends ItemStack {
	
	private ItemInteractAction interactAction;
	private ItemLeftClickAction leftClickAction;
	private ItemRightClickAction rightClickAction;
	
	public ExtendedItem(String displayName, Material material, int customModelData) {
		super(material);
		ItemMeta itemMeta = getItemMeta();
		itemMeta.setDisplayName(ChatColor.RESET + displayName);
		itemMeta.setCustomModelData(customModelData);
		setItemMeta(itemMeta);
	}
	
	public void setDisplayName(String displayName) {
		ItemMeta itemMeta = getItemMeta();
		itemMeta.setDisplayName(ChatColor.RESET + displayName);
		setItemMeta(itemMeta);
	}
	
	public void setCustomModelData(int customModelData) {
		ItemMeta itemMeta = getItemMeta();
		itemMeta.setCustomModelData(customModelData);
		setItemMeta(itemMeta);
	}
	
	public void setLore(String string) {
		String[] list = string.split("\n");
		List<String> lore = new ArrayList<>();
		for (String line : list) lore.add(line);
		ItemMeta itemMeta = getItemMeta();
		itemMeta.setLore(lore);
		setItemMeta(itemMeta);
	}
	
	public void setLore(List<String> lore) {
		ItemMeta itemMeta = getItemMeta();
		itemMeta.setLore(lore);
		setItemMeta(itemMeta);
	}
	
	public void addEnchantment(Enchantment enchantment, int level) {
		ItemMeta itemMeta = getItemMeta();
		itemMeta.addEnchant(enchantment, level, true);
		setItemMeta(itemMeta);
	}
	
	public void addItemFlag(ItemFlag flag) {
		ItemMeta itemMeta = getItemMeta();
		itemMeta.addItemFlags(flag);
		setItemMeta(itemMeta);
	}
	
	public void removeItemFlag(ItemFlag flag) {
		ItemMeta itemMeta = getItemMeta();
		itemMeta.removeItemFlags(flag);
		setItemMeta(itemMeta);
	}
	
	public void addPotionMeta(Color color, PotionEffect effect) {
		if(getType() == Material.POTION) {
			PotionMeta potionMeta = (PotionMeta) getItemMeta();
			potionMeta.setColor(color);
			potionMeta.addCustomEffect(effect, true);
			setItemMeta(potionMeta);
		}
	}
	
	public void addPlayerSkullMeta(Player player) {
		if(getType() == Material.PLAYER_HEAD) {
			SkullMeta skullMeta = (SkullMeta) getItemMeta();
			skullMeta.setOwningPlayer(player);
			setItemMeta(skullMeta);
		}
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
