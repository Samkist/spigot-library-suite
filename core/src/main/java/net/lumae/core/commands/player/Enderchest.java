package net.lumae.core.commands.player;

import net.lumae.core.commands.LumaeExecutor;
import net.lumae.core.modifiers.PlayerStateModifiers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Enderchest extends LumaeExecutor {
    public Enderchest(String commandName) {
        super(commandName);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            Player prospect = Bukkit.getPlayer(args[0]);
            PlayerStateModifiers.openInventory((Player)sender, prospect);
            ((Player)sender).sendMessage("[LunaeMC] You have opened "+prospect.getName()+"'s enderchest!");
        } else {
            PlayerStateModifiers.openEnderchest((Player)sender);
            ((Player)sender).sendMessage("[LunaeMC] Enderchest opened!");
        }
        return true;
    }

    @Override
    public String commandName() {
        return "enderchest";
    }
}
