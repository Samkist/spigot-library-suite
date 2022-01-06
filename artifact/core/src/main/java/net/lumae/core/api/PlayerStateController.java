package net.lumae.core.api;

import net.lumae.core.data.util.Pair;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.function.BiConsumer;

import static net.lumae.core.modifiers.PlayerStateModifiers.*;

public class PlayerStateController {
    private final Player player;

    public PlayerStateController(Player player) {
        this.player = player;
    }

    /*public void health(double level) {
        setHealth(player, level);
    }*/

    public APIResponse<Player, Double> health(double level) {
        return new APIAction<>(p -> setHealth(p, level), player, level);
    }

    public APIResponse<Player, Double> health() {
        return new APIResult<Player, Double>(p -> p.getHealth(), player);
    }

    public APIResponse<Player, Integer> foodLevel(int level) {
       return new APIAction<>(p -> setFoodLevel(p, level), player, level);
    }

    public APIResponse<Player, Integer> foodLevel() {
        return new APIResult<>(p -> p.getFoodLevel(), player);
    }

    public APIResponse<Player, Integer> levels(int levels) {
        return new APIAction<>(p -> setLevel(p, levels), player, levels);
    }

    public APIResponse<Player, Integer> levels() {
        return new APIResult<>(p -> p.getLevel(), player);
    }

    public APIResponse<Player, Integer> addLevels(int levels) {
        return new APIAction<>(p -> addLevel(p, levels), player, levels);
    }

    public APIResponse<Player, String> giveItem(String itemName) {
        return new APIAction<>(p -> give(p, itemName), player, itemName);

    }

    public APIResponse<Player, Pair<String, Integer>> giveItem(String itemName, int amount) {
        BiConsumer<Player, Pair<String, Integer>> biConsumer = (p, pr) -> give(p, pr.getKey(), pr.getValue());
        return new APIAction<>(biConsumer, player, new Pair<>(itemName, amount));
    }

    public APIResponse<Player, PotionEffectType> removeEffect(PotionEffectType type) {
        return new APIAction<>(p -> removePotionEffect(p, type), player, type);
    }

    public APIResponse<Player, Void> removeEffects() {
        return new APIAction<>(p -> cleanse(p), player, null);
    }

    public APIResponse<Player, GameMode> gameMode(GameMode gamemode) {
        return new APIAction<>(p -> p.setGameMode(gamemode), player, gamemode);
    }

    public APIResponse<Player, GameMode> gameMode() {
        return new APIResult<>(p -> p.getGameMode(), player);
    }
    



}
