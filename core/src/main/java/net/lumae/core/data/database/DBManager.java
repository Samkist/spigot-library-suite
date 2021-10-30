package net.lumae.core.data.database;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.model.Sorts;
import net.lumae.core.Core;
import net.lumae.core.data.entities.ChatFormat;
import net.lumae.core.data.entities.JoinLeaveFormat;
import net.lumae.core.data.entities.LumaePlayer;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bukkit.entity.Player;

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
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private String connectionString;
    private MongoCollection<ChatFormat> chatFormats;
    private MongoCollection<JoinLeaveFormat> joinLeaveFormats;
    private MongoCollection<LumaePlayer> players;
    private static final CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    private static final ReplaceOptions replaceOptions = (new ReplaceOptions()).upsert(true);

    public DBManager(Core plugin, String connectionString, String database) {
        this.plugin = plugin;
        this.connectionString = connectionString;
        this.credential = null;
        this.host = null;
        this.database = database;
        this.port = Integer.valueOf(0);
        initialize();
    }

    public DBManager(Core plugin, MongoCredential credential, String host, String database, Integer port) {
        this.plugin = plugin;
        this.credential = credential;
        this.host = host;
        this.database = database;
        this.port = port;
        initialize();
    }

    public void initialize() {
        if (this.init)
            return;
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

    public Optional<ChatFormat> initializeChatFormats(ChatFormat chatFormat) {
        if (!this.init)
            return Optional.empty();
        ChatFormat result = chatFormats.find().first();
        return Objects.nonNull(result) ? Optional.of(result) : Optional.empty();
    }

    public List<ChatFormat> loadChatFormats() {
        ArrayList<ChatFormat> chatFormatsList = new ArrayList<>();
        if (!this.init)
            return chatFormatsList;
        this.chatFormats.find().forEach(chatFormatsList::add);
        return chatFormatsList;
    }

    public Optional<JoinLeaveFormat> initializeJoinLeaveFormats(JoinLeaveFormat format) {
        if (!this.init)
            return Optional.empty();
        return this.joinLeaveFormats.insertOne(format).wasAcknowledged() ? Optional.ofNullable(format) : Optional.empty();
    }

    public List<JoinLeaveFormat> loadJoinLeaveFormats() {
        ArrayList<JoinLeaveFormat> joinLeaveFormatList = new ArrayList<>();
        if (!this.init)
            return joinLeaveFormatList;
        this.joinLeaveFormats.find().forEach(joinLeaveFormatList::add);
        return joinLeaveFormatList;
    }
}
