package dev.samkist.core.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Heal implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p;
        if (args.length == 1) {
            p = Bukkit.getPlayer(args[0]);
        } else {
            p = ((Player)sender);
        }
        if (p instanceof Player) {
            ((Player)sender).setHealth(20.0);
            ((Player)sender).sendMessage("[COMMANDER] Max health granted!");
            return true;
        }
        return false;
    }
}
