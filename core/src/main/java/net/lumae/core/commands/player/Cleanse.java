package net.lumae.core.commands.player;

import net.lumae.core.api.APIPlayer;
import net.lumae.core.api.APIResponse;
import net.lumae.core.api.PlayerStateController;
import net.lumae.core.commands.LumaeExecutor;
import net.lumae.core.modifiers.PlayerStateModifiers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Cleanse extends LumaeExecutor {

    public Cleanse(String commandName) {
        super(commandName);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        PlayerStateController controller = getCore().api().getAPIPlayer((args.length == 1) ? Bukkit.getPlayer(args[0]) : (Player) sender).playerStateController();
        APIResponse<Player, Void> response = controller.removeEffects();
        response.queue((p, Void) -> {
            p.sendMessage("You have been cleansed!");
        });
        return true;
    }
}
