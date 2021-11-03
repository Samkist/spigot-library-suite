package net.lumae.core.data;

import net.lumae.core.Core;
import net.lumae.core.data.database.DBManager;
import net.lumae.core.data.entities.ChatFormat;
import net.lumae.core.data.entities.JoinLeaveFormat;
import net.lumae.core.data.entities.LumaePlayer;
import net.lumae.core.data.entities.Message;
import net.lumae.core.data.local.FileManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class DataManager {
    private static final Core plugin = JavaPlugin.getPlugin(Core.class);
    private final FileManager fileManager;
    private final FileConfiguration defaults;
    private final DBManager dbManager;
    private final Map<UUID, LumaePlayer> players;
    private final Map<String, Message> pluginMessages;
    private List<ChatFormat> chatFormats;
    private List<JoinLeaveFormat> joinLeaveFormats;
    public DataManager(FileManager fileManager, DBManager dbManager) {
        this.fileManager = fileManager;
        this.defaults = fileManager.getDefaultsYml();
        this.dbManager = dbManager;
        this.players = new HashMap<>();
        this.pluginMessages = new HashMap<>();
        initialize();
    }

    private void initialize() {

    }

    public void saveAllPlayers(Map<UUID, LumaePlayer> serverPlayer) {
        dbManager.saveAllPlayers(serverPlayer);
    }

    public LumaePlayer saveLumaePlayer(LumaePlayer lumaePlayer) {
        return dbManager.saveLumaePlayer(lumaePlayer).get();
    }

    public LumaePlayer saveLumaePlayer(Player player) {
        UUID uuid = player.getUniqueId();
        if(players.containsKey(uuid)) {
            return saveLumaePlayer(players.get(uuid));
        }
        return fetchLumaePlayer(player);
    }

    public LumaePlayer login(Player player) {
        return fetchLumaePlayer(player);
    }

    public LumaePlayer loqout(Player player) {
        players.remove(player.getUniqueId());
        return saveLumaePlayer(player);
    }

    public Optional<LumaePlayer> topPlayerByField(String field) {
        return dbManager.topPlayerByField(field);
    }

    public LumaePlayer fetchLumaePlayer(Player player) {
        UUID uuid = player.getUniqueId();
        if(players.containsKey(uuid)) {
            return players.get(uuid);
        } else {
            LumaePlayer lumaePayer = dbManager.loadLumaePlayer(player).get();
            return players.put(uuid, lumaePayer);
        }
    }


    public List<ChatFormat> getDefaultChatFormats() {
        ArrayList<ChatFormat> chatFormats = new ArrayList<>();
        ConfigurationSection chatFormatSection = defaults.getConfigurationSection("chatFormats");
        Set<String> formatKeys = chatFormatSection.getKeys(false);
        formatKeys.stream().map(
                key -> {
                    String permission = chatFormatSection.getString(key + ".permission");
                    String format = chatFormatSection.getString(key + ".messageFormat");
                    Integer priority = chatFormatSection.getInt(key + ".priority");
                    return new ChatFormat(key, permission, format, priority);
                }
        ).forEach(chatFormats::add);
        return chatFormats;
    }

    public List<JoinLeaveFormat> getDefaultJoinLeaveFormats() {
        ArrayList<JoinLeaveFormat> joinLeaveFormats = new ArrayList<>();
        ConfigurationSection joinLeaveSection = defaults.getConfigurationSection("joinLeaveFormats");
        Set<String> formatKeys = joinLeaveSection.getKeys(false);
        formatKeys.stream().map(
                key -> {
                    String permission = joinLeaveSection.getString(key + ".permission");
                    String joinFormat = joinLeaveSection.getString(key + ".joinFormat");
                    String leaveFormat = joinLeaveSection.getString(key + ".leaveFormat");
                    Integer priority = joinLeaveSection.getInt(key + ".priority");
                    return new JoinLeaveFormat(key, permission, joinFormat,  leaveFormat, priority);
                }
        ).forEach(joinLeaveFormats::add);
        return null;
    }

    public List<Message> getDefaultPluginMessages() {
        ArrayList<Message> messages = new ArrayList<>();
        Set<String> messageKeys = defaults.getConfigurationSection("pluginMessages").getKeys(false);
        messageKeys
                .stream().map(
                        key -> new Message(key, defaults.getString("pluginMessages." + key + ".format")))
                .forEach(messages::add);
        return messages;
    }


    public FileManager getFileManager() {
        return fileManager;
    }

    public DBManager getDbManager() {
        return dbManager;
    }
}
