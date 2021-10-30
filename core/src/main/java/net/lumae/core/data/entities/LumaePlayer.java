package net.lumae.core.data.entities;

import org.bson.types.Decimal128;
import org.bukkit.entity.Player;

import java.util.Calendar;

public class LumaePlayer {
    private String uuid;
    private String lastUsername;
    private Decimal128 balance;
    private Integer votes;
    private Integer secondsPlayed;
    private Boolean banned;
    private String joinDate;
    private String banReason;

    public LumaePlayer(Player player) {
        this.uuid = player.getUniqueId().toString();
        this.lastUsername = player.getName();
        this.balance = new Decimal128(0);
        this.votes = 0;
        this.banReason = "";
        this.secondsPlayed = 0;
        this.banned = false;
        this.joinDate = Calendar.getInstance().getTime().toString();
    }

    public LumaePlayer() {
        //DUMMY
    }

    public LumaePlayer(String uuid, String lastUsername, Decimal128 balance, Integer votes, Integer secondsPlayed, String joinDate) {
        this.uuid = uuid;
        this.lastUsername = lastUsername;
        this.balance = balance;
        this.banReason = "";
        this.votes = votes;
        this.secondsPlayed = secondsPlayed;
        this.banned = false;
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

    public void setBanned(Boolean banned) { this.banned = banned; }

    public boolean isBanned() { return this.banned; }

    public String getBanReason() { return this.banReason; }

    public void setBanReason(String reason) { this.banReason = reason; }
}
