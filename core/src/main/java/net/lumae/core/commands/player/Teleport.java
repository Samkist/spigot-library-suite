package net.lumae.core.commands.player;

import net.lumae.core.commands.LumaeExecutor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Teleport extends LumaeExecutor {
    public Teleport(String commandName) {
        super(commandName);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p1 = Bukkit.getPlayer(args[0]);
        switch(args.length) {
            case 1:
                ((Player)sender).teleport(p1);
                break;
            case 2:
                Player p2 = Bukkit.getPlayer(args[1]);
                p1.teleport(p2);
                break;
            default:
                return false;
        }
        return true;
    }
}
