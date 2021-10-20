package dev.samkist.core.data;

import dev.samkist.core.Core;
import dev.samkist.core.data.database.DBManager;
import dev.samkist.core.data.entities.ChatFormat;
import dev.samkist.core.data.entities.JoinLeaveFormat;
import dev.samkist.core.data.entities.ServerPlayer;
import dev.samkist.core.data.local.FileManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class DataManager {
    private static final Core plugin = JavaPlugin.getPlugin(Core.class);
    private final FileManager fileManager;
    private final DBManager dbManager;
    private final Map<UUID, ServerPlayer> ServerPlayerMap;
    //private final Map<String, Message> pluginMessages;
    private final List<ChatFormat> chatFormats;
    private final List<JoinLeaveFormat> joinLeaveFormats;
    public DataManager(FileManager fileManager, DBManager dbManager) {
        this.fileManager = fileManager;
        this.dbManager = dbManager;
        this.ServerPlayerMap = new HashMap<>();
        //this.pluginMessages = new HashMap<>();
        this.chatFormats = loadChatFormats();
        this.joinLeaveFormats = loadJoinLeaveFormats();
        //initialize();
    }

    /*private void initialize() {
        final List<Message> msgs = dbManager.loadMessages();
        msgs.forEach(m -> pluginMessages.put(m.getMessageId(), m));
    }*/

    public void saveAllPlayers(Map<UUID, ServerPlayer> serverPlayer) {
        dbManager.saveAllPlayers(serverPlayer);
    }

    public void saveServerPlayer(Player player, ServerPlayer serverPlayer) {
        dbManager.saveServerPlayer(player, serverPlayer);
    }

    public Optional<ServerPlayer> topPlayerByField(String field) {
        return dbManager.topPlayerByField(field);
    }

    public Optional<ServerPlayer> loadServerPlayer(Player player) {
        return dbManager.loadServerPlayer(player);
    }

    public void initializeServerPlayer(Player player) {
        dbManager.initializeServerPlayer(player);
    }

    public ServerPlayer fetchServerPlayer(Player player) {
        return ServerPlayerMap.get(player.getUniqueId());
    }

    public ServerPlayer fetchServerPlayer(UUID uuid) {
        return ServerPlayerMap.get(uuid);
    }

    public List<ChatFormat> loadChatFormats() {
        return dbManager.loadChatFormats();
    }

    public List<JoinLeaveFormat> loadJoinLeaveFormats() {
        return dbManager.loadJoinLeaveFormats();
    }

    /*public Optional<Message> messageById(String id) {
        return Optional.ofNullable(pluginMessages.get(id));
    }*/

    public FileManager getFileManager() {
        return fileManager;
    }

    public DBManager getDbManager() {
        return dbManager;
    }

    public Map<UUID, ServerPlayer> getServerPlayerMap() {
        return ServerPlayerMap;
    }

    /*public Map<String, Message> getPluginMessages() {
        return pluginMessages;
    }*/

    public List<ChatFormat> getChatFormats() {
        return chatFormats;
    }

    public List<JoinLeaveFormat> getJoinLeaveFormats() {
        return joinLeaveFormats;
    }
}
