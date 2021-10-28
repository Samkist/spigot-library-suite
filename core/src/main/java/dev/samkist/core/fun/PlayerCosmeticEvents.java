package dev.samkist.core.fun;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

/*
Have not checked functionality of Listeners
 */

public class PlayerCosmeticEvents implements Listener {
    @EventHandler
    public void onSpawn(PlayerSpawnLocationEvent e) {
        if (e.getPlayer().hasPermission("vip.spawn_firework")) {
            Firework fw = (Firework) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.FIREWORK);
            FireworkMeta fwm = fw.getFireworkMeta();
            fwm.setPower(2);
            fwm.addEffect(FireworkEffect.builder().withColor(Color.BLUE).flicker(true).build());
            fw.setFireworkMeta(fwm);
            fw.detonate();
        }
    }
    @EventHandler
    public void particleTrail(PlayerMoveEvent e) {
        if (e.getPlayer().hasPermission("vip.particle_trail")) {
            e.getPlayer().spawnParticle(Particle.ELECTRIC_SPARK, e.getPlayer().getLocation(), 10);
        }
    }
}
