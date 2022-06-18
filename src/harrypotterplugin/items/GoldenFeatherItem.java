package harrypotterplugin.items;

import harrypotterplugin.utilities.ExtendedItemStack;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Objects;

public class GoldenFeatherItem extends ExtendedItemStack implements Listener {

    public GoldenFeatherItem() {
        super("Golden Feather", Material.CARROT_ON_A_STICK, 22);
        setLore(ChatColor.GRAY + "Protects you from fall damage with the power of \"arresto momentum\"");
        registerEvents();
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getCause() == DamageCause.FALL && event.getDamage() != 0) {
            final ArrayList<ItemStack> brokenItems = new ArrayList<>();
            for (ItemStack itemStack : ((Player) event.getEntity()).getInventory()) {
                if (ExtendedItemStack.isItem(itemStack, Material.CARROT_ON_A_STICK, 22)) {
                    double damage = event.getDamage();
                    if (Objects.requireNonNull(itemStack.getItemMeta()).hasEnchant(Enchantment.DURABILITY)) {
                        if (itemStack.getItemMeta().getEnchantLevel(Enchantment.DURABILITY) == 1) {
                            damage = damage / 1.5;
                        } else if (itemStack.getItemMeta().getEnchantLevel(Enchantment.DURABILITY) == 2) {
                            damage = damage / 2;
                        } else if (itemStack.getItemMeta().getEnchantLevel(Enchantment.DURABILITY) == 3) {
                            damage = damage / 2.5;
                        }
                    }
                    if ((itemStack.getDurability() + damage < 25)) {
                        itemStack.setDurability((short) (itemStack.getDurability() + damage));
                        event.setDamage(0);
                    } else {
                        event.setDamage(event.getDamage() - (25 - itemStack.getDurability()));
                        itemStack.setDurability((short) 25);
                        brokenItems.add(itemStack);
                    }
                }
            }
            for (ItemStack brokenItem : brokenItems) {
                ((Player) event.getEntity()).getInventory().remove(brokenItem);
            }
        }
    }

}
