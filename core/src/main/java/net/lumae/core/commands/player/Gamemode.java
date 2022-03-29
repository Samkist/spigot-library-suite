package net.lumae.core.commands.player;

import net.lumae.core.commands.LumaeExecutor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Gamemode extends LumaeExecutor {
    public Gamemode(String commandName) {
        super(commandName);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        GameMode gamemode;
        Player p;
        switch(args.length) {
            case 0:
                p = (Player)sender;
                gamemode = (p.getGameMode() == GameMode.SURVIVAL) ? GameMode.CREATIVE: GameMode.SURVIVAL;
                break;
            case 1:
                p = (Player)sender;
                gamemode = GameMode.valueOf(args[0]);
                break;
            case 2:
                p = Bukkit.getPlayer(args[0]);
                gamemode = GameMode.valueOf(args[1]);
                break;
            default:
                return false;
        }
        p.setGameMode(gamemode);
        sender.sendMessage("Gamemode set!");
        return true;
    }

    @Override
    public String commandName() {
        return "gamemode";
    }
}
