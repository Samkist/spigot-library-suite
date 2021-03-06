package net.lumae.core.commands.player;

import net.lumae.core.modifiers.PlayerStateModifiers;
import net.lumae.core.commands.LumaeExecutor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Xp extends LumaeExecutor {
    public Xp(String commandName) {
        super(commandName);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length != 2 || args.length != 3) return false;
        switch(args[0]) {
            case "get":
                sender.sendMessage("[LunaeMC] Player "+ Bukkit.getPlayer(args[1]).getName()+" is level "+String.valueOf(PlayerStateModifiers.getLevel(Bukkit.getPlayer(args[1])))+"!");
                break;
            case "set":
                PlayerStateModifiers.setLevel(Bukkit.getPlayer(args[1]), Integer.valueOf(args[2]));
                sender.sendMessage("[LunaeMC] You have set "+Bukkit.getPlayer(args[1]).getName()+"'s level to "+String.valueOf(PlayerStateModifiers.getLevel(Bukkit.getPlayer(args[1])))+"!");
                break;
            case "add":
                PlayerStateModifiers.addLevel(Bukkit.getPlayer(args[1]), Integer.valueOf(args[2]));
                sender.sendMessage("[LunaeMC] You have set "+Bukkit.getPlayer(args[1]).getName()+"'s level to "+String.valueOf(PlayerStateModifiers.getLevel(Bukkit.getPlayer(args[1])))+"!");
                break;
        }
        return true;
    }
}
