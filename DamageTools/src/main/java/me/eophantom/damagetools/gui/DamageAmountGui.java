package me.eophantom.damagetools.gui;

import me.eophantom.damagetools.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class DamageAmountGui {

    public final Inventory inv;
    private Setter setter;
    public int damageAmount = 10;

    public DamageAmountGui(Setter setter) {
        inv = Bukkit.createInventory(null,9,"Damage Amount");

        this.setter = setter;
        setter.setAmount(this);
        initializeItems();
    }

    public void initializeItems() {
        inv.setItem(0,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        inv.setItem(1,createGuiItem(Material.RED_STAINED_GLASS_PANE, "-100 Damage", "remove 100 damage", ""));
        inv.setItem(2,createGuiItem(Material.RED_STAINED_GLASS_PANE, "-10 Damage", "remove 10 damage", ""));
        inv.setItem(3,createGuiItem(Material.RED_STAINED_GLASS_PANE, "-5 Damage", "remove 5 damage", ""));
        inv.setItem(4,createGuiItem(Material.YELLOW_STAINED_GLASS_PANE, "Current Damage: " + damageAmount, "§6Change the value items take each time", "§cValues can not be negative!"));
        inv.setItem(5,createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "§a+5 Damage", "Add 5 damage", ""));
        inv.setItem(6,createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "§a+10 Damage", "Add 10 damage", ""));
        inv.setItem(7,createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "§a+100 Damage", "Add 100 damage", ""));
        inv.setItem(8,createGuiItem(Material.RED_STAINED_GLASS_PANE, "Go Back", "Go back to the config panel", ""));
    }

    public ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
    }
}
