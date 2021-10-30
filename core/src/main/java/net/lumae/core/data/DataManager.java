package net.lumae.core.data;

import net.lumae.core.Core;
import net.lumae.core.data.database.DBManager;
import net.lumae.core.data.entities.ChatFormat;
import net.lumae.core.data.entities.JoinLeaveFormat;
import net.lumae.core.data.entities.LumaePlayer;
import net.lumae.core.data.local.FileManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class DataManager {
    private static final Core plugin = JavaPlugin.getPlugin(Core.class);
    private final FileManager fileManager;
    private final DBManager dbManager;
    private final Map<UUID, LumaePlayer> ServerPlayerMap;
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

    public void saveAllPlayers(Map<UUID, LumaePlayer> serverPlayer) {
        dbManager.saveAllPlayers(serverPlayer);
    }

    public Optional<LumaePlayer> saveServerPlayer(LumaePlayer lumaePlayer) {
        return dbManager.saveLumaePlayer(lumaePlayer);
    }

    public Optional<LumaePlayer> topPlayerByField(String field) {
        return dbManager.topPlayerByField(field);
    }

    public Optional<LumaePlayer> loadServerPlayer(Player player) {
        return dbManager.loadServerPlayer(player);
    }

    public Optional<LumaePlayer> initializeServerPlayer(Player player) {
        return dbManager.initializeServerPlayer(player);
    }

    public LumaePlayer fetchLumaePlayer(Player player) {
        return ServerPlayerMap.get(player.getUniqueId());
    }

    public LumaePlayer fetchLumaePlayer(UUID uuid) {
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

    public Map<UUID, LumaePlayer> getServerPlayerMap() {
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
