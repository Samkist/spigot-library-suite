package dev.samkist.commander.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Gamemode implements CommandExecutor {
    private void setSurvival(Player p) {
        p.setGameMode(GameMode.SURVIVAL);
        p.sendMessage("[COMMANDER] Gamemode set to survival!");
    }
    private void setCreative(Player p) {
        p.setGameMode(GameMode.CREATIVE);
        p.sendMessage("[COMMANDER] Gamemode set to creative!");
    }
    private void changeGamemode(Player p) {
        if (p.getGameMode() == GameMode.SURVIVAL) {
            this.setCreative(p);
        } else {
            this.setSurvival(p);
        }
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0 && sender instanceof Player) {
            this.changeGamemode((Player)sender);
            return true;
        } else if (args.length == 1 && sender instanceof Player) {
            if (args[0] == "survival" || Integer.valueOf(args[0]) == 0) {
                this.setSurvival((Player)sender);
            } else if (args[0] == "creative" || Integer.valueOf(args[0]) == 1) {
                this.setCreative((Player)sender);
            } else return false;
            return true;
        } else if (args.length == 2) {
            Player p = Bukkit.getPlayer(args[0]);
            if (!p.isEmpty() && (args[1].toLowerCase() == "survival" || Integer.valueOf(args[1]) == 0)) {
                this.setSurvival(p);
                return true;
            } else if (!p.isEmpty() && (args[1].toLowerCase() == "creative" || Integer.valueOf(args[1]) == 1)) {
                this.setCreative(p);
                return true;
            }
        }
        return false;
    }
}