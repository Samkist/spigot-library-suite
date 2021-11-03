package net.lumae.core.data.database;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import net.lumae.core.Core;
import net.lumae.core.data.DataManager;
import net.lumae.core.data.entities.ChatFormat;
import net.lumae.core.data.entities.JoinLeaveFormat;
import net.lumae.core.data.entities.LumaePlayer;
import net.lumae.core.data.entities.Message;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static com.mongodb.client.model.Filters.*;

public class DBManager {
    private final Core plugin;
    private final MongoCredential credential;
    private final String host;
    private final String database;
    private final Integer port;
    private boolean init;
    private DataManager dataManager;
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private String connectionString;
    private MongoCollection<ChatFormat> chatFormats;
    private MongoCollection<JoinLeaveFormat> joinLeaveFormats;
    private MongoCollection<LumaePlayer> players;
    private MongoCollection<Message> pluginMessages;
    private static final CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
    fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    private static final ReplaceOptions replaceOptions = (new ReplaceOptions()).upsert(true);

    public DBManager(Core plugin, String connectionString, String database) {
        this.plugin = plugin;
        this.dataManager = plugin.getDataManager();
        this.connectionString = connectionString;
        this.credential = null;
        this.host = null;
        this.database = database;
        this.port = Integer.valueOf(0);
    }

    public DBManager(Core plugin, MongoCredential credential, String host, String database, Integer port) {
        this.plugin = plugin;
        this.dataManager = plugin.getDataManager();
        this.credential = credential;
        this.host = host;
        this.database = database;
        this.port = port;
    }

    public void initialize() {
        if (this.init)
            return;
        dataManager = plugin.getDataManager();
        if (Objects.nonNull(this.connectionString)) {
            this.mongoClient = MongoClients.create(MongoClientSettings.builder()
                    .codecRegistry(codecRegistry)
                    .applyConnectionString(new ConnectionString(this.connectionString))
                    .build());
        } else {
            this.mongoClient = MongoClients.create(
                    MongoClientSettings.builder()
                            .applyToClusterSettings(builder -> builder.hosts(Arrays.asList(new ServerAddress(host, port)))).codecRegistry(codecRegistry)
                            .credential(this.credential)
                            .build());
        }
        this.init = true;
        this.mongoDatabase = this.mongoClient.getDatabase(this.database);
        this.chatFormats = this.mongoDatabase.getCollection("chatFormats", ChatFormat.class);
        this.joinLeaveFormats = this.mongoDatabase.getCollection("joinLeaveFormats", JoinLeaveFormat.class);
        this.players = this.mongoDatabase.getCollection("players", LumaePlayer.class);
        this.pluginMessages = this.mongoDatabase.getCollection("pluginMessages", Message.class);
    }

    public void saveAllPlayers(Map<UUID, LumaePlayer> serverPlayerMap) {
        List<ReplaceOneModel<LumaePlayer>> replaceModels =
                new ArrayList<>();
        for (LumaePlayer player : serverPlayerMap.values()) {
            ReplaceOneModel<LumaePlayer> uuid = new ReplaceOneModel<>(
                    eq("uuid", player.getUuid()),
                    player,
                    replaceOptions);
            replaceModels.add(uuid);
        }
        this.players.bulkWrite(replaceModels);
    }

    public Optional<LumaePlayer> topPlayerByField(String field) {
        if (!this.init)
            return Optional.empty();
        return Optional.ofNullable(this.players.find().sort(Sorts.descending(field)).first());
    }

    public List<LumaePlayer> topPlayersByField(String field) {
        if (!this.init)
            return List.of();
        ArrayList<LumaePlayer> players = new ArrayList<>();
        Objects.requireNonNull(players);
        this.players.find().sort(Sorts.descending(field)).forEach(players::add);
        return players;
    }

    public Optional<LumaePlayer> saveLumaePlayer(LumaePlayer serverPlayer) {
        if (!this.init)
            return Optional.empty();
        return players.replaceOne(
                Filters.eq("uuid", serverPlayer.getUuid()), serverPlayer, replaceOptions).wasAcknowledged() ? Optional.of(serverPlayer) : Optional.empty();
    }

    public Optional<LumaePlayer> initializeLumaePlayer(Player player) {
        if (!this.init)
            return Optional.empty();
        LumaePlayer data = new LumaePlayer(player);
        return this.players.insertOne(data).wasAcknowledged() ? Optional.of(data) : Optional.empty();
    }

    public Optional<LumaePlayer> loadLumaePlayer(Player player) {
        if (!this.init)
            return Optional.empty();
        LumaePlayer serverPlayer = players.find(eq("uuid", player.getUniqueId().toString())).first();
        return Objects.nonNull(serverPlayer) ? Optional.of(serverPlayer) : initializeLumaePlayer(player);
    }

    private List<ChatFormat> initializeChatFormats(List<ChatFormat> formats) {
        return chatFormats.insertMany(formats).wasAcknowledged() ? formats : null;
    }

    public List<ChatFormat> loadChatFormats() {
        ArrayList<ChatFormat> formats = new ArrayList<>();
        if (!this.init)
            return formats;
        if(!isEmpty(chatFormats)) {
            this.chatFormats.find().forEach(formats::add);
        } else {
            formats.addAll(
                    initializeChatFormats(dataManager.getDefaultChatFormats())
            );
        }
        return formats;
    }

    private List<JoinLeaveFormat> initializeJoinLeaveFormats(List<JoinLeaveFormat> formats) {
        return joinLeaveFormats.insertMany(formats).wasAcknowledged() ? formats : null;
    }

    /**
     * Function will check if there are any already initialized formats
     * If not, it will call {@link DBManager#initializeJoinLeaveFormats(List)},
     * supplying the default values loaded from defaults.yml
     *
     * @return the list of loaded JoinLeaveFormats
     */
    public List<JoinLeaveFormat> loadJoinLeaveFormats() {
        ArrayList<JoinLeaveFormat> formats = new ArrayList<>();
        if (!this.init)
            return formats;
        if(!isEmpty(joinLeaveFormats)) {
            this.joinLeaveFormats.find().forEach(formats::add);
        } else {
            formats.addAll(
                    initializeJoinLeaveFormats(dataManager.getDefaultJoinLeaveFormats())
            );
        }
        return formats;
    }

    private List<Message> initializeMessages(List<Message> messages) {
        return pluginMessages.insertMany(messages).wasAcknowledged() ? messages : null;
    }

    public List<Message> loadMessages() {
        ArrayList<Message> messages = new ArrayList<>();
        if (!this.init)
            return messages;
        if(!isEmpty(pluginMessages)) {
            this.pluginMessages.find().forEach(messages::add);
        } else {
            messages.addAll(
                    initializeMessages(dataManager.getDefaultPluginMessages())
            );
        }
        return messages;
    }

    private boolean isEmpty(MongoCollection collection) {
        return Objects.isNull(collection.find().first());
    }

}
