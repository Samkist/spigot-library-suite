package dev.samkist.core.economy;

import dev.samkist.core.storage.ServerPlayer;
import org.bson.types.Decimal128;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Economy implements CommandExecutor {
    public void recipt(Player p) {
        p.sendMessage("[ECONOMY] Balance: $"+new ServerPlayer(p).getBalance().toString());
    }
    public void compareRecipt(Player player, Player prospect) {
        player.sendMessage("[ECONOMY] You have $"+String.valueOf(Math.abs(this.get(player) - this.get(prospect)))+((this.get(player) > this.get(prospect)) ? " more " : " fewer "+"dollars!"));
    }
    public boolean compare(Player player, Player prospect) {
        return (this.get(player) > this.get(prospect));
    }
    public boolean atleast(Player p, int amount) {
        return (amount <= this.get(p));
    }
    public boolean transfer(Player player, Player prospect, int amount) {
        if (this.atleast(player, amount)) {
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
        new ServerPlayer(p).setBalance(Decimal128.parse(String.valueOf(amount)));
    }
    public void deposit(Player p, int amount) {
        new ServerPlayer(p).setBalance(Decimal128.parse(String.valueOf(new ServerPlayer(p).getBalance().intValue() + amount)));
    }
    public void withdraw(Player p, int amount) {
         this.deposit(p, -1*amount);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        switch (command.getName()) {
            case "balance":
                if (args.length == 1) {
                    this.recipt(Bukkit.getPlayer(args[0]));
                } else {
                    if (!(sender instanceof Player)) return false;
                    this.recipt((Player) sender);
                }
                break;
            case "baltop":
                //TODO: Sort players in DB by balance
                break;
            case "pay":
                if (!this.transfer((Player) sender, Bukkit.getPlayer(args[0]), Integer.valueOf(args[1]))) return false;
                break;
            case "eco":
                Player p = Bukkit.getPlayer(args[1]);
                int amount = Integer.valueOf(args[2]);
                if (args[0] == "set") {
                    this.set(p, amount);
                } else if (args[0] == "deposit") {
                    this.deposit(p, amount);
                } else if (args[0] == "withdraw") {
                    this.withdraw(p, amount);
                } else if (args[0] == "compare") {
                    this.compareRecipt((Player)sender, Bukkit.getPlayer(args[0]));
                } else {
                    return false;
                }
                break;
        }
        return true;
    }
}
