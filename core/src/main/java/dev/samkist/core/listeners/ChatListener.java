package dev.samkist.core.listeners;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import dev.samkist.core.Core;
import dev.samkist.core.data.entities.ChatFormat;
import dev.samkist.core.utils.ColorUtil;
import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Comparator;
import java.util.List;

public class ChatListener implements Listener {

    private static final Core plugin = JavaPlugin.getPlugin(Core.class);
    private final List<ChatFormat> chatFormats;

    public ChatListener() {
        chatFormats = plugin.getDataManager().getChatFormats();
    }

    @EventHandler
    public void chatEvent(AsyncChatEvent event) {
        final Player player = event.getPlayer();
        final ChatFormat format = chatFormats.stream().filter(f ->
                        player.hasPermission(f.getPermission()))
                .min(Comparator.comparing(ChatFormat::getPriority)).get();

        final ChatRenderer chatFormat = (chatPlayer, displayName, msg, viewer) -> {
            final String metaApplied = PlaceholderAPI.setPlaceholders(player, format.getMessageFormat());
            final String message = metaApplied.replace("%player_message%", LegacyComponentSerializer.legacyAmpersand().serialize(msg));

            return GsonComponentSerializer.gson()
                    .deserialize(ColorUtil.legacyToJson(IridiumColorAPI.process(message)));
        };
        event.renderer(chatFormat);

    }

}