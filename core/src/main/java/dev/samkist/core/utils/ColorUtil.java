package dev.samkist.core.utils;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import dev.samkist.core.Core;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.plugin.java.JavaPlugin;

public class ColorUtil {
    private static final Core plugin = JavaPlugin.getPlugin(Core.class);

    /**
     * Converts a legacy message (one you would read in chat) to a json message.
     *
     * @param legacyString the legacy message to convert
     * @return the message converted to json
     */
    public static String legacyToJson(String legacyString) {
        return legacyString == null ? "" : ComponentSerializer.toString(TextComponent.fromLegacyText(legacyString));
    }


    /**
     * Converts a json message to a legacy message.
     *
     * @param json the json message to convert
     * @return the legacy message
     */
    public static String jsonToLegacy(String json) {
        return json == null ? "" : BaseComponent.toLegacyText(ComponentSerializer.parse(json));
    }



    public static String colorMessage(String legacyMsg) {
        return IridiumColorAPI.process(legacyMsg);
    }
}
