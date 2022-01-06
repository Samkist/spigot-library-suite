package net.lumae.core.commands.player;

import net.lumae.core.api.APIResponse;
import net.lumae.core.api.PlayerStateController;
import net.lumae.core.commands.LumaeExecutor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Feed extends LumaeExecutor {
    public Feed(String commandName) {
        super(commandName);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        PlayerStateController controller = getCore().api().getAPIPlayer((args.length == 1) ? Bukkit.getPlayer(args[0]) : (Player) sender).playerStateController();
        APIResponse<Player, Integer> response = controller.foodLevel(20);
        response.queue((p, num) -> {
            p.sendMessage("You have been fed!");
        });
        return true;
    }

    @Override
    public String commandName() {
        return "feed";
    }
}
