package dev.samkist.core.data.database;


import com.mongodb.*;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.aggregation.experimental.Aggregation;
import dev.morphia.aggregation.experimental.stages.Group;
import dev.morphia.aggregation.experimental.stages.Sort;
import dev.morphia.query.Query;
import dev.morphia.query.experimental.filters.Filters;
import dev.morphia.query.experimental.updates.UpdateOperators;
import dev.samkist.core.Core;
import dev.samkist.core.data.entities.ChatFormat;
import dev.samkist.core.data.entities.JoinLeaveFormat;
import dev.samkist.core.data.entities.ServerPlayer;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.*;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DBManager {
    private final Core plugin;
    private final MongoCredential credential;
    private final String host;
    private final String database;
    private final Integer port;
    private boolean init;
    private MongoClient mongoClient;
    private String connectionString;
    private Collection<ChatFormat> chatFormats;
    private Collection<JoinLeaveFormat> joinLeaveFormats;
    private Collection<ServerPlayer> serverPlayers;
    //PLAIN OLD JAVA OBJECTS
    final CodecRegistry codecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    public DBManager(Core plugin, String connectionString, String database) {
        this.plugin = plugin;
        this.connectionString = connectionString;
        this.credential = null;
        this.host = null;
        this.database = database;
        this.port = 0;
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
        if(init) return;
        if(Objects.nonNull(connectionString)) {
            MongoClientOptions.Builder mongoClientOptions = MongoClientOptions.builder()
                    .codecRegistry(codecRegistry);
            MongoClientURI uri = new MongoClientURI(connectionString, mongoClientOptions);
            this.mongoClient = new MongoClient(uri);

        } else {
           /* this.mongoClient = MongoClients.create(
                    MongoClientSettings.builder()
                            .applyToClusterSettings(builder ->
                                    builder.hosts(Arrays.asList(new ServerAddress(host, port))))
                            .codecRegistry(codecRegistry)
                            .credential(credential)
                            .build());*/
        }
        datastore =
        init = true;
    }

    public void saveAllPlayers(Map<UUID, ServerPlayer> ServerPlayer) {
        datastore.withTransaction(s
                -> s.save(new ArrayList<>(ServerPlayer.values())));
    }

    public Optional<ServerPlayer> topPlayerByField(String field) {
        if(!init) return Optional.empty();
        final Aggregation<ServerPlayer> aggregation = datastore.aggregate(ServerPlayer.class).group(Group.group(Group.id(field)).field(field)
        ).sort(Sort.sort().descending(field));
        return Optional.ofNullable(aggregation.execute(ServerPlayer.class).toList().get(0));
    }

    public List<ServerPlayer> topPlayersByField(String field) {
        if(!init) return List.of();
        final Aggregation<ServerPlayer> aggregation = datastore.aggregate(ServerPlayer.class).group(Group.group(Group.id(field)).field(field)
        ).sort(Sort.sort().descending(field));
        return aggregation.execute(ServerPlayer.class).toList();
    }

    public void saveServerPlayer(Player player, ServerPlayer ServerPlayer) {
        if(!init) return;

        final Query<ServerPlayer> query = datastore.find(ServerPlayer.class).filter(Filters.eq("uuid", player.getUniqueId().toString()));


        if(Objects.nonNull(query)) {
            query.update(UpdateOperators.set(ServerPlayer)).execute();
        } else {
            plugin.getLogger().info("Initializing from savePlayer");
            initializeServerPlayer(player);
        }
    }

    public void initializeServerPlayer(Player player) {
        if(!init) return;
        final ServerPlayer data = new ServerPlayer(player);
        datastore.save(data);
    }

    public Optional<ServerPlayer> loadServerPlayer(Player player) {
        if(!init) return Optional.empty();
        final ServerPlayer result = datastore.find("ServerPlayer", ServerPlayer.class)
                .filter(Filters.eq("uuid", player.getUniqueId().toString()))
                .first();
        if(Objects.isNull(result)) {
            initializeServerPlayer(player);
            loadServerPlayer(player);
        }
        return Optional.ofNullable(result);
    }

    public void initializeChatFormats(ChatFormat chatFormat) {
        if(!init) return;
        final ChatFormat result = datastore.find(ChatFormat.class).first();
        if(Objects.isNull(result)) {
            datastore.save(chatFormat);
        }
    }

    public List<ChatFormat> loadChatFormats() {
        if(!init) return new ArrayList<>();
        final ChatFormat result = datastore.find(ChatFormat.class).first();
        if(Objects.nonNull(result)) {
            return datastore.find(ChatFormat.class)
                    .iterator().toList();
        } else {
            final FileConfiguration config = plugin.getFileManager().getConfigYml();
            final String name = "default";
            final String permission = config.getString("defaults.chatFormats.permission");
            final String format = config.getString("default.chatFormats.messageFormat");
            final Integer priority = config.getInt("default.chatFormats.priority");
            initializeChatFormats(new ChatFormat(name, permission, format, priority));
            return loadChatFormats();
        }
    }

    public void initializeJoinLeaveFormats(JoinLeaveFormat format) {
        if(!init) return;
        datastore.save(format);
    }

    public List<JoinLeaveFormat> loadJoinLeaveFormats() {
        if(!init) return new ArrayList<>();
        final JoinLeaveFormat result = datastore.find(JoinLeaveFormat.class).first();
        if(Objects.nonNull(result)) {
            return datastore.find(JoinLeaveFormat.class).iterator().toList();
        } else {
            final FileConfiguration config = plugin.getFileManager().getConfigYml();
            final String name = "default";
            final String permission = config.getString("defaults.joinLeaveFormats.permission");
            final String messageFormat = config.getString("defaults.joinLeaveFormats.messageFormat");
            final int priority = config.getInt("defaults.joinLeaveFormats.priority");
            final JoinLeaveFormat format = new JoinLeaveFormat(name, permission, messageFormat, priority);
            initializeJoinLeaveFormats(format);
            return loadJoinLeaveFormats();
        }
    }




}
