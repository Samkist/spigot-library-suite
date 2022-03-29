package net.lumae.customsaddles.commands;

import net.lumae.customsaddles.items.Saddle;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GetSaddle implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p;
        EntityType e;
        int amount = 1;
        switch(args.length) {
            case 1:
                e = EntityType.valueOf(args[0]);
                p = (Player)sender;
                break;
            case 2:
                p = Bukkit.getPlayer(args[0]);
                e = EntityType.valueOf(args[1]);
                break;
            case 3:
                p = Bukkit.getPlayer(args[0]);
                e = EntityType.valueOf(args[1]);
                amount = Integer.valueOf(args[2]);
                break;
            default:
                return false;
        }
        try {
            p.getInventory().addItem(new Saddle(e));
        } catch (IllegalArgumentException error) {
            return false;
        }
        return true;
    }
}
