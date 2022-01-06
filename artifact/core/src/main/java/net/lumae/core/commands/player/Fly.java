package net.lumae.core.commands.player;

import net.lumae.core.commands.LumaeExecutor;
import net.lumae.core.modifiers.PlayerStateModifiers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Fly extends LumaeExecutor {
    PlayerStateModifiers state;
    public Fly(String commandName, PlayerStateModifiers state) {
        super(commandName);
        this.state = state;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        switch(args.length) {
            case 0:
                PlayerStateModifiers.toggleFlight((Player) sender);
            case 1:
                handler((Player)sender, args[0]);
                break;
            case 2:
                Player p = Bukkit.getPlayer(args[0]);
                handler(p, args[1]);
                break;
            default:
                return false;
        }
        return true;
    }

    public void handler(Player p, String str) {
        if (enableCase(str)) {
            PlayerStateModifiers.enableFlight(p);
        } else if (disableCase(str)) {
            PlayerStateModifiers.disableFlight(p);
        }
    }

    public boolean enableCase(String str) {
        return (
                str.equalsIgnoreCase("true") ||
                str.equalsIgnoreCase("1") ||
                str.equalsIgnoreCase("on")
        );
    }

    public boolean disableCase(String str) {
        return (
                str.equalsIgnoreCase("false") ||
                        str.equalsIgnoreCase("0") ||
                        str.equalsIgnoreCase("off")
        );
    }

    @Override
    public String commandName() {
        return "fly";
    }
}
