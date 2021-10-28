package dev.samkist.core.economy;

import dev.samkist.core.Core;
import dev.samkist.core.data.entities.ServerPlayer;
import org.bson.types.Decimal128;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Economy implements CommandExecutor {

    private static final Core plugin = JavaPlugin.getPlugin(Core.class);

    public void receipt(Player p) {
        p.sendMessage("[ECONOMY] Balance: $" + new ServerPlayer(p).getBalance().toString());
    }

    public void getOtherReceipt(Player recipient, Player prospect) {
        recipient.sendMessage("[ECONOMY] Balance: $" + new ServerPlayer(prospect).getBalance().toString());
    }

    public void compareReceipt(Player player, Player prospect) {
        player.sendMessage("[ECONOMY] You have $" + Math.abs(this.get(player) - this.get(prospect)) + ((this.get(player) > this.get(prospect)) ? " more " : " fewer " + "dollars!"));
    }

    public boolean atLeast(Player p, int amount) {
        return (amount <= this.get(p));
    }

    public boolean transfer(Player player, Player prospect, int amount) {
        if (this.atLeast(player, amount)) {
            this.withdraw(player, amount);
            this.deposit(prospect, amount);
            return true;
        }
        return false;
    }

    public int get(Player p) {
        return new ServerPlayer(p).getBalance().intValue();
    }

    public void set(Player p, int amount) {
        new ServerPlayer(p)
                .setBalance(
                        Decimal128.parse(
                                String.valueOf(amount)
                        ));
    }

    public void deposit(Player p, int amount) {
        new ServerPlayer(p)
                .setBalance(
                        Decimal128.parse(
                                String.valueOf(
                                        new ServerPlayer(p)
                                                .getBalance()
                                                .intValue() + amount
                                )));
    }

    public void withdraw(Player p, int amount) {
        this.deposit(p, -1 * amount);
    }
    public boolean attemptWithdraw(Player p, int amount) {
        if (this.atLeast(p, amount)) {
            this.withdraw(p, amount);
            return true;
        }
        return false;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        switch (command.getName()) {
            case "balance":
                if (args.length == 1) {
                    this.getOtherReceipt((Player) sender, Bukkit.getPlayer(args[0]));
                    break;
                }
                if (!(sender instanceof Player)) return false;
                this.receipt((Player) sender);
                break;
            case "baltop":
                //TODO: Sort players in Query
                List<ServerPlayer> baltopPlayers = plugin.getDbManager().topPlayersByField("balance");
                break;
            case "pay":
                if (!this.transfer((Player) sender, Bukkit.getPlayer(args[0]), Integer.valueOf(args[1]))) return false;
                break;
            case "eco":
                Player p = Bukkit.getPlayer(args[1]);
                int amount = Integer.valueOf(args[2]);
                if (args[0].equalsIgnoreCase("set")) {
                    this.set(p, amount);
                } else if (args[0].equalsIgnoreCase("deposit")) {
                    this.deposit(p, amount);
                } else if (args[0].equalsIgnoreCase("withdraw")) {
                    this.withdraw(p, amount);
                } else if (args[0].equalsIgnoreCase("compare")) {
                    this.compareReceipt((Player) sender, Bukkit.getPlayer(args[0]));
                } else {
                    return false;
                }
                break;
        }
        return true;
    }
}
