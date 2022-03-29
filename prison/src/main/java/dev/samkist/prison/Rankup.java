package dev.samkist.prison;

import net.lumae.core.api.APIPlayer;
import net.lumae.core.api.CoreAPI;
import net.lumae.core.data.entities.LumaePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Rankup implements CommandExecutor {
    CoreAPI coreApi;
    public Rankup(CoreAPI coreApi) {
        this.coreApi = coreApi;
    }
    private class Group {
        private String name;
        public double amount;
        public void setAmount(double amount) {
            this.amount = amount;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
    private ArrayList<Group> ranks;
    public Group getGroup(String name) {
        for (Group group : this.ranks) {
            if (group.name == name) return group;
        }
        return null;
    }
    public void populate() {
        //Look through YML containing list of RANK_NAME && RANK_PRICE. List in order of price...
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Group prospectedRank;
        for(int i = 0; i < this.ranks.size(); i++) {
            //Finds first group not in, retrieves price.
            if (!((Player)sender).hasPermission("prison.".concat(this.ranks.get(i).toString()))) {
                prospectedRank = this.ranks.get(i);
                break;
            }
        }
        //CHECK BALANCE && Rankup if enough $$$
        return false;
    }
}
