package net.lumae.core.economy;

import net.lumae.core.Core;
import net.lumae.core.data.DataManager;
import net.lumae.core.data.entities.LumaePlayer;
import org.bson.types.Decimal128;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.math.BigDecimal;

public class Economy {

    private final Core core = JavaPlugin.getPlugin(Core.class);
    private final DataManager dataManager;

    public Economy() {
        dataManager = core.getDataManager();
    }

    public void receipt(Player p) {
        p.sendMessage("[ECONOMY] Balance: $" + new LumaePlayer(p).getBalance().toString());
    }

    public void getOtherReceipt(Player recipient, Player prospect) {
        recipient.sendMessage("[ECONOMY] Balance: $" + new LumaePlayer(prospect).getBalance().toString());
    }

    public void compareReceipt(Player player, Player prospect) {
        player.sendMessage("[ECONOMY] You have $" + Math.abs(this.get(player) - this.get(prospect)) + ((this.get(player) > this.get(prospect)) ? " more " : " fewer " + "dollars!"));
    }

    private boolean atLeast(Player p, double amount) {
        return (amount <= this.get(p));
    }

    public boolean transfer(Player player, Player prospect, double amount) {
        if (this.atLeast(player, amount)) {
            this.withdraw(player, amount);
            this.deposit(prospect, amount);
            return true;
        }
        return false;
    }

    private double get(Player p) {
        return dataManager.fetchLumaePlayer(p).getBalance().doubleValue(); //TODO change to double
    }

    public void set(Player p, double amount) {
        dataManager.fetchLumaePlayer(p)
                .setBalance(new Decimal128(new BigDecimal((double) Math.abs(amount))));
    }

    public void deposit(Player p, double amount) {
        dataManager.fetchLumaePlayer(p)
                .setBalance(new Decimal128(new BigDecimal((double) Math.abs(amount))));
    }

    public void withdraw(Player p, double amount) {
        this.deposit(p, -1 * Math.abs(amount));
    }
    public boolean attemptWithdraw(Player p, double amount) {
        if (this.atLeast(p, Math.abs(amount))) {
            this.withdraw(p, Math.abs(amount));
            return true;
        }
        return false;
    }
}
