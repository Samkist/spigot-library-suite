package net.lumae.core.commands.player;

import net.lumae.core.commands.LumaeExecutor;
import net.lumae.core.modifiers.PlayerStateModifiers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

public class Godmode extends LumaeExecutor implements Listener {
    PlayerStateModifiers state;
    public Godmode(String commandName, PlayerStateModifiers state) {
        super(commandName);
        this.state = state;
    }
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled=false)
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && this.state.isGodmode((Player)e.getEntity())) e.setCancelled(true);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        switch(args.length) {
            case 0:
                state.toggleGodmode((Player)sender);
                break;
            case 1:
                handler((Player)sender, args[0]);
                break;
            case 2:
                handler(Bukkit.getPlayer(args[0]), args[1]);
                break;
            default:
                return false;
        }
        return true;
    }

    public void handler(Player p, String str) {
        if (enableCase(str)) {
            PlayerStateModifiers.enableGodmode(p);
        } else if (disableCase(str)) {
            PlayerStateModifiers.disableGodmode(p);
        }
    }

    public boolean enableCase(String str) {
        return (
                str.equalsIgnoreCase("true") ||
                        str.equalsIgnoreCase("1") ||
                        str.equalsIgnoreCase("on")
        );
    }

    public boolean disableCase(String str) {
        return (
                str.equalsIgnoreCase("false") ||
                        str.equalsIgnoreCase("0") ||
                        str.equalsIgnoreCase("off")
        );
    }

    @Override
    public String commandName() {
        return "godmode";
    }
}
