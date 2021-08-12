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

public class ItemBuilder {
	
	private String displayname;
	private Material material;
	private List<String> lore;
	private int custommodeldata;
	private ItemStack itemstack;
	private ItemMeta itemmeta;
	
	public ItemBuilder() {
		displayname = "Example Soward";
		material = Material.WOODEN_SWORD;
		custommodeldata = 0;
		itemmeta = new ItemStack(material).getItemMeta();
	}
	
	public ItemBuilder(Material material) {
		displayname = "Example Soward";
		this.material = material;
		custommodeldata = 0;
		itemmeta = new ItemStack(material).getItemMeta();
	}
	
	public ItemBuilder(String displayname) {
		this.displayname = displayname;
		material = Material.WOODEN_SWORD;
		custommodeldata = 0;
		itemmeta = new ItemStack(material).getItemMeta();
	}
	
	public ItemBuilder(String displayname, Material material) {
		this.displayname = displayname;
		this.material = material;
		itemmeta = new ItemStack(material).getItemMeta();
	}
	
	public void setDisplayName(String displayname) {
		this.displayname = displayname;
	}
	
	public String getDisplayName() {
		return displayname;
	}
	
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	public Material getMaterial() {
		return material;
	}
	
	public void setLore(List<String> lore) {
		this.lore = lore;
	}
	
	public void setLore(String string) {
		String[] list = string.split("\n");
		List<String> lore = new ArrayList<>();
		for (int i = 0; i < list.length; i++) {
			lore.add(list[i]);
		}
		this.lore = lore;
	}
	
	public List<String> getLore() {
		return lore;
	}
	
	public void setCustomModelData(int costummodeldata) {
		this.custommodeldata = costummodeldata;
	}
	
	public int getCustommodeldata() {
		return custommodeldata;
	}
	
	public void addEnchantment(Enchantment enchantment, int level) {
		itemmeta.addEnchant(enchantment, level, true);
	}
	
	public void hideEnchantments() {
		itemmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	}
	
	public void addPotionMeta(Color color, PotionEffect potioneffect) {
		if(itemstack != null && material == Material.POTION) {
			PotionMeta potionmeta = (PotionMeta) itemmeta;
			potionmeta.setColor(color);
			potionmeta.addCustomEffect(potioneffect, true);
			itemstack.setItemMeta(potionmeta);
		}
	}
	
	public void addPlayerSkullMeta(Player player) {
		if(itemstack != null && material == Material.PLAYER_HEAD) {
			SkullMeta skullmeta = (SkullMeta) itemmeta;
			skullmeta.setOwningPlayer(player);
			itemstack.setItemMeta(skullmeta);
		}
	}
	
	public ItemStack buildItem() {
		itemstack = new ItemStack(material);
		buildItem(itemstack);
		return itemstack;
	}
	
	public void buildItem(ItemStack itemstack) {
		itemmeta.setDisplayName(ChatColor.RESET + displayname);
		if(lore != null) {
			itemmeta.setLore(lore);
		}
		itemmeta.setCustomModelData(custommodeldata);
		itemstack.setItemMeta(itemmeta);
	}
	
	public ItemMeta getItemMeta() {
		if(itemstack == null) {
			return buildItem().getItemMeta();
		}
		return itemstack.getItemMeta();
	}
	
	public ItemStack getItemStack() {
		if(itemstack == null) {
			return buildItem();
		}
		return itemstack;
	}

}
