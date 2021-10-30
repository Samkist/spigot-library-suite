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

    private boolean atLeast(Player p, int amount) {
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

    private int get(Player p) {
        return dataManager.fetchLumaePlayer(p).getBalance().intValue(); //TODO change to double
    }

    public void set(Player p, int amount) {
        dataManager.fetchLumaePlayer(p)
                .setBalance(new Decimal128(new BigDecimal((double) amount)));
    }

    public void deposit(Player p, int amount) {
        dataManager.fetchLumaePlayer(p)
                .setBalance(new Decimal128(new BigDecimal((double) amount)));
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
}
