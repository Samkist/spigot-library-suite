package net.lumae.core.commands.player;

import net.lumae.core.api.APIResponse;
import net.lumae.core.api.PlayerStateController;
import net.lumae.core.commands.LumaeExecutor;
import net.lumae.core.data.util.Pair;
import net.lumae.core.modifiers.PlayerStateModifiers;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Give extends LumaeExecutor {
    public Give(String commandName) {
        super(commandName);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p;
        String itemName;
        int amount = 1;
        switch(args.length) {
            case 1:
                p = (Player)sender;
                itemName = args[0];
                break;
            case 2:
                p = (Player)sender;
                itemName = args[0];
                amount = Integer.valueOf(args[1]);
                break;
            case 3:
                p = Bukkit.getPlayer(args[0]);
                itemName = args[1];
                amount = Integer.valueOf(args[2]);
                break;
            default:
                return false;
        }
        PlayerStateController controller = getCore().api().getAPIPlayer(p).playerStateController();
        APIResponse<Player, Pair<String, Integer>> response = controller.giveItem(itemName, amount);
        response.queue((player, item) -> {
            player.sendMessage("You have received ".concat(String.valueOf(item.getValue())).concat("x ").concat(item.getKey()).concat("(s)."));
        });
        return true;
    }
}
