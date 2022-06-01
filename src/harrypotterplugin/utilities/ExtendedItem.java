package harrypotterplugin.utilities;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
		itemMeta.setDisplayName(displayName);
		itemMeta.setCustomModelData(customModelData);
		setItemMeta(itemMeta);
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
