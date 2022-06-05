package me.eophantom.damagetools.gui;

import me.eophantom.damagetools.Setter;
import me.eophantom.damagetools.listeners.DamageListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ManageExceptionGUI implements Listener {

    public final Inventory inv;
    private Setter setter;

    private DamageListener damageListener;

    public ManageExceptionGUI(DamageListener damageListener) {

        this.damageListener = damageListener;

        inv = Bukkit.createInventory(null,9, "Manage Exceptions");
    }

    public void initializeItems() {
        inv.setItem(0,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        inv.setItem(1,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        inv.setItem(2,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        inv.setItem(3,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        if (damageListener.exceptions.contains(damageListener.managePlayer)) {
            inv.setItem(4,createGuiItem(Material.ORANGE_STAINED_GLASS_PANE, "Remove " + damageListener.managePlayer.getDisplayName(), "§6Remove player as an exception", "§players tools will take damage"));
        } else {
            inv.setItem(4,createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "Add " + damageListener.managePlayer.getDisplayName(), "§6Add player as an exception", "§cplayers tools will not take damage"));
        }
        inv.setItem(5,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        inv.setItem(6,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        inv.setItem(7,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        inv.setItem(8,createGuiItem(Material.RED_STAINED_GLASS_PANE, "Go Back", "Go back to the exceptions panel", ""));
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

    // You can open the inventory with this
    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
        inv.clear();
        initializeItems();
    }


}
