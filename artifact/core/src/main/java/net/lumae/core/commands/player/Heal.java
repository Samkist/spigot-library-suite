package net.lumae.core.commands.player;

import net.lumae.core.api.APIResponse;
import net.lumae.core.api.PlayerStateController;
import net.lumae.core.commands.LumaeExecutor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Heal extends LumaeExecutor {
    public Heal(String commandName) {
        super(commandName);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        PlayerStateController p = getCore().api().getAPIPlayer((args.length == 1) ? Bukkit.getPlayer(args[0]) : (Player)sender).playerStateController();
        APIResponse<Player, Double> res = p.health(20.0);
        res.queue((player, health) -> {
           player.sendMessage("You have been healed!");
        });
        return true;
    }
}
