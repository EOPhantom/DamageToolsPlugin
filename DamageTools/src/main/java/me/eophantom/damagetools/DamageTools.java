package me.eophantom.damagetools;

import me.eophantom.damagetools.commands.DamageConfigCommands;
import me.eophantom.damagetools.gui.DamageAmountGui;
import me.eophantom.damagetools.gui.DamageConfigGui;
import me.eophantom.damagetools.gui.DamageExceptionGui;
import me.eophantom.damagetools.listeners.DamageListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class DamageTools extends JavaPlugin{

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("DamageTools online");
        Setter myObj = new Setter();
        new DamageConfigGui(myObj);
        new DamageAmountGui(myObj);
        new DamageExceptionGui(myObj);
        getServer().getPluginManager().registerEvents(new DamageListener(myObj), this);
        getCommand("config").setExecutor(new DamageConfigCommands(myObj));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
