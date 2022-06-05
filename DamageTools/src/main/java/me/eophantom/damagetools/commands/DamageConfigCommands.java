package me.eophantom.damagetools.commands;

import me.eophantom.damagetools.Setter;
import me.eophantom.damagetools.gui.DamageConfigGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

public class DamageConfigCommands implements CommandExecutor {

    private Setter setter;
    private DamageConfigGui damageConfigGui;

    public DamageConfigCommands(Setter setter){
        this.setter = setter;
        damageConfigGui = setter.getConfig();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)){
            return false;
        }
            commandSender.sendMessage("Opening Config");
            damageConfigGui.openInventory((HumanEntity) commandSender);
        return true;
    }
}
