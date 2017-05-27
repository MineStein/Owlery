package me.tylergrissom.godricsowlery.listener;

import me.tylergrissom.godricsowlery.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Copyright Tyler Grissom 2017
 */
public class ChatListener implements Listener {

    private Main plugin;

    public Main getPlugin() {
        return plugin;
    }

    public ChatListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (plugin.getChatTracker().getCreateAnnouncement().containsKey(player.getUniqueId())) {
            if (event.getMessage().equalsIgnoreCase("!cancel")) {
                // TODO Cancel


            } else {

            }
        }
    }
}
