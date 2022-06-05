package me.eophantom.damagetools.listeners;

import me.eophantom.damagetools.Setter;
import me.eophantom.damagetools.gui.DamageAmountGui;
import me.eophantom.damagetools.gui.DamageConfigGui;
import me.eophantom.damagetools.gui.DamageExceptionGui;
import me.eophantom.damagetools.gui.ManageExceptionGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class DamageListener implements Listener {

    private Setter setter;
    private DamageAmountGui damageAmountGui;
    private DamageExceptionGui damageExceptionGui;
    private DamageConfigGui damageConfigGui;
    private ManageExceptionGUI manageExceptionGUI;
    public Player managePlayer;
    public List<Player> exceptions = new ArrayList<>();
    public DamageListener(Setter setter){
        this.setter = setter;
        setter.setDamageListener(this);
        damageAmountGui = setter.getAmount();
        damageConfigGui = setter.getConfig();
        damageExceptionGui = setter.getException();
        manageExceptionGUI = new ManageExceptionGUI(this);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (damageConfigGui.toggle) {
                if (!(exceptions.contains((Player) event.getEntity()))) {
                    Player player = (Player) event.getEntity();
                    double damageValue = damageAmountGui.damageAmount;
                    Inventory playerInventory = player.getInventory();
                    int itemIndex = randomPlayerItem(playerInventory);
                    ItemStack playerItem = playerInventory.getItem(itemIndex);
                    ItemMeta playerItemMeta = playerItem.getItemMeta();
                    org.bukkit.inventory.meta.Damageable playerItemMetaDamage = (org.bukkit.inventory.meta.Damageable) playerItemMeta;
                    if (playerItemMetaDamage.getDamage() < playerItem.getType().getMaxDurability() - (int) damageValue){
                        playerItemMetaDamage.setDamage(playerItemMetaDamage.getDamage() + (int) damageValue);
                        playerItem.setItemMeta(playerItemMetaDamage);
                    } else {
                        playerInventory.clear(itemIndex);
                    }
                }
            }

        }
    }

    public int randomPlayerItem(Inventory playerInventory){
        List<Integer> items = new ArrayList<>();
        for (int i = 0; i < 36; i++) {
            if (!(playerInventory.getItem(i) == null) && Objects.requireNonNull(playerInventory.getItem(i)).getType().getMaxDurability() > 0) {
                items.add(i);
            }
        }
        int item = new Random().nextInt(items.size());
        return items.get(item);
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getInventory().equals(damageAmountGui.inv)) {
            e.setCancelled(true);

            final ItemStack clickedItem = e.getCurrentItem();

            // verify current item is not null
            if (clickedItem == null || clickedItem.getType().isAir()) return;

            final Player p = (Player) e.getWhoClicked();

            if (e.getRawSlot() == 1) {
                damageAmountGui.damageAmount -= 100;
            } else if (e.getRawSlot() == 2) {
                damageAmountGui.damageAmount -= 10;
            } else if (e.getRawSlot() == 3) {
                damageAmountGui.damageAmount -= 5;
            } else if (e.getRawSlot() == 5) {
                damageAmountGui.damageAmount += 5;
            } else if (e.getRawSlot() == 6) {
                damageAmountGui.damageAmount += 10;
            } else if (e.getRawSlot() == 7) {
                damageAmountGui.damageAmount += 100;
            } else if (e.getRawSlot() == 8) {
                p.closeInventory();
                damageConfigGui.openInventory(p);
            }
            damageAmountGui.inv.setItem(4,damageAmountGui.createGuiItem(Material.YELLOW_STAINED_GLASS_PANE, "Current Damage: " + damageAmountGui.damageAmount, "§6Change the value items take each time", "§cValues can not be negative!"));
            // Using slots click is a best option for your inventory click's
            p.sendMessage("You clicked at slot " + e.getRawSlot());
        } else if (e.getInventory().equals(damageConfigGui.inv)) {

            e.setCancelled(true);

            final ItemStack clickedItem = e.getCurrentItem();

            // verify current item is not null
            if (clickedItem == null || clickedItem.getType().isAir()) return;

            final Player p = (Player) e.getWhoClicked();

            if(e.getRawSlot() == 2) {
                if (damageConfigGui.toggle) {
                    damageConfigGui.toggle = false;
                    damageConfigGui.inv.setItem(2,damageConfigGui.createGuiItem(Material.RED_STAINED_GLASS_PANE, "Toggle DamageTools", "Green = Plugin is active", "Red = Plugin is disabled"));
                } else {
                    damageConfigGui.toggle = true;
                    damageConfigGui.inv.setItem(2,damageConfigGui.createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "Toggle DamageTools", "Green = Plugin is active", "Red = Plugin is disabled"));
                }
            } else if (e.getRawSlot() == 4) {
                p.closeInventory();
                damageAmountGui.openInventory(p);
            } else if (e.getRawSlot() == 6) {
                p.closeInventory();
                damageExceptionGui.openInventory(p);
            }

            // Using slots click is a best option for your inventory click's
            p.sendMessage("You clicked at slot " + e.getRawSlot());
        } else if (e.getInventory().equals(damageExceptionGui.inv)) {
            e.setCancelled(true);

            final ItemStack clickedItem = e.getCurrentItem();

            // verify current item is not null
            if (clickedItem == null || clickedItem.getType().isAir()) return;

            final Player p = (Player) e.getWhoClicked();

            if (e.getRawSlot() == 53) {
                p.closeInventory();
                damageConfigGui.openInventory(p);
            } else if (Objects.equals(e.getCurrentItem(), new ItemStack(Material.GRAY_STAINED_GLASS_PANE))) {
                return;
            } else {
                managePlayer = Bukkit.getPlayerExact(e.getCurrentItem().getItemMeta().getDisplayName());
                p.closeInventory();
                manageExceptionGUI.openInventory(p);
            }
        } else if (e.getInventory().equals(manageExceptionGUI.inv)){
            e.setCancelled(true);

            final ItemStack clickedItem = e.getCurrentItem();

            // verify current item is not null
            if (clickedItem == null || clickedItem.getType().isAir()) return;

            final Player p = (Player) e.getWhoClicked();

            if (e.getRawSlot() == 8) {
                p.closeInventory();
                damageExceptionGui.openInventory(p);
            } else if (e.getRawSlot() == 4) {
                if (exceptions.contains(managePlayer)) {
                    exceptions.remove(managePlayer);
                    manageExceptionGUI.inv.setItem(4,manageExceptionGUI.createGuiItem(Material.ORANGE_STAINED_GLASS_PANE, "Remove " + managePlayer.getDisplayName(), "§6Remove player as an exception", "§players tools will take damage"));
                } else {
                    exceptions.add(managePlayer);
                    manageExceptionGUI.inv.setItem(4,manageExceptionGUI.createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "Add " + managePlayer.getDisplayName(), "§6Add player as an exception", "§cplayers tools will not take damage"));
                }
            }
        }
    }

    // Cancel dragging in our inventory
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory().equals(damageAmountGui.inv) || e.getInventory().equals(damageExceptionGui.inv) || e.getInventory().equals(damageConfigGui.inv)) {
            e.setCancelled(true);
        }
    }

}
