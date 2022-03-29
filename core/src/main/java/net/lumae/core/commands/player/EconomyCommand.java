package net.lumae.core.commands.player;

import net.lumae.core.commands.LumaeExecutor;
import net.lumae.core.data.entities.LumaePlayer;
import net.lumae.core.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class EconomyCommand extends LumaeExecutor {

    private final Economy economy;

    public EconomyCommand(String commandName, Economy economy) {
        super(commandName);
        this.economy = economy;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] argsArray) {
        List<String> args = Arrays.asList(argsArray);
        switch (command.getName()) {
            case "balance":
                if (args.size() == 1) {
                    economy.getOtherReceipt((Player) sender, Bukkit.getPlayer(args.get(0)));
                    break;
                }
                if (!(sender instanceof Player)) return false;
                economy.receipt((Player) sender);
                break;
            case "baltop":
                //TODO: Sort players in Query
                List<LumaePlayer> baltopPlayers = getCore().getDbManager().topPlayersByField("balance");
                break;
            case "pay":
                if (!economy.transfer((Player) sender, Bukkit.getPlayer(args.get(0)), Double.valueOf(args.get(1)))) return false;
                break;
            case "economy":
                Player p = Bukkit.getPlayer(args.get(0));
                double amount = Double.valueOf(args.get(2));
                if (args.get(1).equalsIgnoreCase("set")) {
                    economy.set(p, amount);
                } else if (args.get(1).equalsIgnoreCase("deposit")) {
                    economy.deposit(p, amount);
                } else if (args.get(1).equalsIgnoreCase("withdraw")) {
                    economy.withdraw(p, amount);
                } else if (args.get(1).equalsIgnoreCase("compare")) {
                    economy.compareReceipt((Player) sender, Bukkit.getPlayer(args.get(0)));
                } else {
                    return false;
                }
                break;
        }
        return true;
    }

    @Override
    public String commandName() {
        return "economy";
    }
}