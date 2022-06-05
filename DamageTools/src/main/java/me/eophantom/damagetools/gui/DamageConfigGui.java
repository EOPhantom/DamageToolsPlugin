package me.eophantom.damagetools.gui;

import me.eophantom.damagetools.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class DamageConfigGui {
    public final Inventory inv;
    public boolean toggle = false;
    DamageExceptionGui damageExceptionGui;
    DamageAmountGui damageAmountGui;
    private Setter setter;

    public DamageConfigGui(Setter setter) {
        inv = Bukkit.createInventory(null,9,"Config");

        this.setter = setter;
        setter.setConfig(this);
        damageAmountGui = setter.getAmount();
        damageExceptionGui = setter.getException();
        initializeItems();
    }

    // You can call this whenever you want to put the items in
    public void initializeItems() {
        inv.setItem(0,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        inv.setItem(1,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        if (toggle) {
            inv.setItem(2,createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "Toggle DamageTools", "§aGreen = Plugin is active", "§cRed = Plugin is disabled"));
        } else {
            inv.setItem(2,createGuiItem(Material.RED_STAINED_GLASS_PANE, "Toggle DamageTools", "§aGreen = Plugin is active", "§cRed = Plugin is disabled"));
        }

        inv.setItem(3,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        inv.setItem(4,createGuiItem(Material.ORANGE_STAINED_GLASS_PANE, "Set Damage Amount", "§6Change the value items take each time", "§cValue can not be negative!"));
        inv.setItem(5,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        inv.setItem(6,createGuiItem(Material.PURPLE_STAINED_GLASS_PANE, "Exceptions", "§dSet Player who are exempt", "§dfrom the plugin"));
        inv.setItem(7,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        inv.setItem(8,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
    }

    // Nice little method to create a gui item with a custom name, and description
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

    // You can open the inventory with this
    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
    }
}
