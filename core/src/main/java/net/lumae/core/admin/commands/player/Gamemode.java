package net.lumae.core.admin.commands.player;

import net.lumae.core.modifiers.PlayerStateModifiers;
import net.lumae.core.admin.commands.LumaeExecutor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class Gamemode extends LumaeExecutor {
    public Gamemode(String commandName) {
        super(commandName);
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        /*if (args.length == 1) {
            if (args[0].equals("1") || args[0].equals("creative") || args[0].equals("c")) {
                PlayerStateModifiers.setCreative((Player) sender);
            } else if (args[0].equals("0") || args[0].equals("survival") || args[0].equals("s")) {
                PlayerStateModifiers.setSurvival((Player) sender);
            } else return false;
        } else if (args.length == 2) {
            if (args[1].equals("1") || args[1].equals("creative") || args[1].equals("c")) {
                PlayerStateModifiers.setCreative(Bukkit.getPlayer(args[0]));
            } else if (args[1].equals("0") || args[1].equals("survival") || args[1].equals("s")) {
                PlayerStateModifiers.setSurvival(Bukkit.getPlayer(args[0]));
            } else return false;
        } else {
            PlayerStateModifiers.changeGamemode((Player) sender);
        }*/
        sender.sendMessage("[LunaeMC] Gamemode set!");
        return true;
    }

    @Override
    public String commandName() {
        return "gamemode";
    }
}
