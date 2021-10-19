package dev.samkist.commander.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Give implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        switch(args.length) {
            case 1:
                ((Player)sender).getInventory().addItem(new ItemStack(Material.matchMaterial(args[0])));
                break;
            case 2:
                ((Player)sender).getInventory().addItem(new ItemStack(Material.matchMaterial(args[0]), Integer.valueOf(args[1])));
                break;
            case 3:
                Bukkit.getPlayer(args[0]).getInventory().addItem(new ItemStack(Material.matchMaterial(args[1]), Integer.valueOf(args[2])));
        }
        return true;
    }
}