package net.lumae.core.api;

import net.lumae.core.data.util.Pair;
import net.lumae.core.economy.Economy;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;

public class EconomyController {
    private final Player player;
    private final Economy economy;

    public EconomyController(Player player, Economy economy) {
        this.player = player;
        this.economy = economy;
    }

    public APIResponse<Player, Void> receipt() {
        return new APIAction<>(p -> economy.receipt(p), player, null);
    }

    //TODO change to double
    public APIResponse<Player, Double> transfer(Player target, Double amount) {
        BiConsumer<Player, Double> biConsumer = (p, amt) -> economy.transfer(player, p, amt);
        return new APIAction<>(biConsumer, target, amount);
    }
    //TODO change to double
    public APIResponse<Player, Double> set(Double amount) {
        return new APIAction<>(p -> economy.set(p, amount), player, amount);
    }

    //TODO change to double
    public APIResponse<Player, Double> deposit(Double amount) {
        return new APIAction<>(p -> economy.deposit(p, amount), player, amount);
    }



    //TODO change to double
    public APIResponse<Player, Double> withdraw(Double amount) {
        return new APIAction<>(p -> economy.withdraw(p, amount), player, amount);
    }

    public APIResponse<Pair<Player, Double>, Boolean> attemptWithdraw(Double amount) { //TODO: Better way to implement?
        return new APIResult<>(p -> economy.attemptWithdraw(p.getKey(), p.getValue()), new Pair<>(player, amount));
    }
}
