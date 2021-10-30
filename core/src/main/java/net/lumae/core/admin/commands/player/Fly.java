package net.lumae.core.admin.commands.player;

import net.lumae.core.admin.PlayerStateModifiers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Fly implements CommandExecutor {
    public String command = "fly";
    PlayerStateModifiers state;
    public Fly(PlayerStateModifiers state) {
        this.state = state;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 2) {
            if (args[1].equals("1") || args[1].equals("true")) {
                PlayerStateModifiers.enableFlight(Bukkit.getPlayer(args[0]));
            } else if (args[1].equals("0") || args[1].equals("false")) {
                PlayerStateModifiers.disableFlight(Bukkit.getPlayer(args[0]));
            }
        } else if (args.length == 1) {
            if (args[0].equals("1") || args[0].equals("true")) {
                PlayerStateModifiers.enableFlight((Player)sender);
            } else if (args[0].equals("0") || args[0].equals("false")) {
                PlayerStateModifiers.disableFlight((Player)sender);
            }
        } else {
            PlayerStateModifiers.toggleFlight((Player)sender);
        }
        return true;
    }
}
