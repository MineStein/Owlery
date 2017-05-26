package me.tylergrissom.godricsowlery.chat;

import me.tylergrissom.godricsowlery.Main;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Copyright Tyler Grissom 2017
 */
public class ChatTracker {

    private Main plugin;
    private HashMap<Player, String> createAnnouncement;

    public Main getPlugin() {
        return plugin;
    }

    public HashMap<Player, String> getCreateAnnouncement() {
        return createAnnouncement;
    }

    public ChatTracker(Main plugin) {
        this.plugin = plugin;
        this.createAnnouncement = new HashMap<>();
    }
}
