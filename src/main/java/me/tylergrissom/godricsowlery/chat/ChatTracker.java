package me.tylergrissom.godricsowlery.chat;

import me.tylergrissom.godricsowlery.Main;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Copyright Tyler Grissom 2017
 */
public class ChatTracker {

    private Main plugin;
    private HashMap<UUID, String> createAnnouncement;

    public Main getPlugin() {
        return plugin;
    }

    public HashMap<UUID, String> getCreateAnnouncement() {
        return createAnnouncement;
    }

    public ChatTracker(Main plugin) {
        this.plugin = plugin;
        this.createAnnouncement = new HashMap<>();
    }
}
