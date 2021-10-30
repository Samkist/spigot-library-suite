package net.lumae.core.api;

import net.lumae.core.economy.Economy;
import org.bukkit.entity.Player;

public class EconomyController {
    private final Player player;
    private final Economy economy;

    public EconomyController(Player player, Economy economy) {
        this.player = player;
        this.economy = economy;
    }

    public void receipt() {
        economy.receipt(player);
    }

    //TODO change to double
    public boolean transfer(Player target, Double amount) {
        return economy.transfer(player, target, amount.intValue());
    }
    //TODO change to double
    public void set(Double amount) {
        economy.set(player, amount.intValue());
    }

    //TODO change to double
    public void deposit(Double amount) {
        economy.deposit(player, amount.intValue());
    }

    //TODO change to double
    public void withdraw(Double amount) {
        economy.withdraw(player, amount.intValue());
    }
}
