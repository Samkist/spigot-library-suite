package dev.samkist.core.admin.commands;

import dev.samkist.core.admin.PlayerStateModifiers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

public class Godmode implements CommandExecutor, Listener {
    PlayerStateModifiers state;
    public Godmode(PlayerStateModifiers state) {
        this.state = state;
    }
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled=false)
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && this.state.isGodmode((Player)e.getEntity())) e.setCancelled(true);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 2) {
            if (args[1].equals("1") || args[1].equals("true")) {
                this.state.enableGodmode(Bukkit.getPlayer(args[0]));
            } else if (args[1].equals("0") || args[1].equals("false")) {
                this.state.disableGodmode(Bukkit.getPlayer(args[0]));
            } else return false;
        } else if (args.length == 1) {
            if (args[0].equals("1") || args[0].equals("true")) {
                this.state.enableGodmode((Player)sender);
            } else if (args[0].equals("0") || args[0].equals("false")) {
                this.state.disableGodmode((Player)sender);
            } else return false;
        } else {
            this.state.toggleGodmode((Player)sender);
        }
        return true;
    }
}
