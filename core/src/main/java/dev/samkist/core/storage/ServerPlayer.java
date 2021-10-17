package dev.samkist.core.storage;

import dev.morphia.annotations.Entity;
import dev.morphia.mapping.codec.references.MorphiaProxy;
import org.bson.types.Decimal128;
import org.bukkit.entity.Player;

import java.util.Calendar;
import java.util.UUID;

@Entity(value = "serverPlayer", useDiscriminator = false)
public class ServerPlayer {
    private String uuid;
    private String lastUsername;
    private Decimal128 balance;
    private Integer votes;
    private Integer secondsPlayed;
    private String joinDate;

    public ServerPlayer(Player player) {
        MorphiaProxy.class.getName();
        this.uuid = player.getUniqueId().toString();
        this.lastUsername = player.getName();
        this.balance = new Decimal128(0);
        this.votes = 0;
        this.secondsPlayed = 0;
        this.joinDate = Calendar.getInstance().getTime().toString();
    }

    public ServerPlayer() {
        //DUMMY
    }

    public ServerPlayer(String uuid, String lastUsername, Decimal128 balance, Integer votes, Integer secondsPlayed, String joinDate) {
        this.uuid = uuid;
        this.lastUsername = lastUsername;
        this.balance = balance;
        this.votes = votes;
        this.secondsPlayed = secondsPlayed;
        this.joinDate = joinDate;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLastUsername() {
        return lastUsername;
    }

    public void setLastUsername(String lastUsername) {
        this.lastUsername = lastUsername;
    }

    public Decimal128 getBalance() {
        return balance;
    }

    public void setBalance(Decimal128 balance) {
        this.balance = balance;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public Integer getSecondsPlayed() {
        return secondsPlayed;
    }

    public void setSecondsPlayed(Integer secondsPlayed) {
        this.secondsPlayed = secondsPlayed;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
}
