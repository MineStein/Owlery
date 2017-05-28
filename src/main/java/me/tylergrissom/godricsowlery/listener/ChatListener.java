package me.tylergrissom.godricsowlery.listener;

import me.tylergrissom.godricsowlery.Main;
import me.tylergrissom.godricsowlery.chat.ChatTracker;
import me.tylergrissom.godricsowlery.menu.YesNoCancelGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

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
    public void onChat(final AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        final ChatTracker tracker = plugin.getChatTracker();

        if (tracker.getCreateAnnouncement().containsKey(player.getUniqueId())) {
            event.setCancelled(true);

            final ChatTracker.ChatData data = tracker.getCreateAnnouncement().get(player.getUniqueId());

            if (event.getMessage().equalsIgnoreCase("!cancel")) {
                String type = data.getType().equals(ChatTracker.ChatData.Type.CREATE) ? "creation" : "editing";

                player.sendMessage("§bYou cancelled the " + type + " of the announcement.");

                tracker.getCreateAnnouncement().remove(player.getUniqueId());
            } else {
                if (data.getType().equals(ChatTracker.ChatData.Type.CREATE)) {
                    List<String> announcements = plugin.getConfig().getStringList("announcement-settings.announcements");

                    announcements.add(event.getMessage());

                    plugin.getConfig().set("announcement-settings.announcements", announcements);
                    plugin.saveConfig();

                    tracker.getCreateAnnouncement().remove(player.getUniqueId());

                    player.sendMessage("§bYou have created the following announcement: §a" + event.getMessage());
                } else {
                    YesNoCancelGUI confirmDeleteGui = new YesNoCancelGUI("Confirm Edit", plugin) {
                        @Override
                        public void onFinish(Response response) {
                            if (response.equals(Response.YES)) {
                                int id = data.getId();

                                List<String> announcements = plugin.getConfig().getStringList("announcement-settings.announcements");

                                announcements.remove(id);
                                announcements.add(event.getMessage());

                                plugin.getConfig().set("announcement-settings.announcements", announcements);
                                plugin.saveConfig();

                                tracker.getCreateAnnouncement().remove(player.getUniqueId());

                                player.sendMessage("§bYou have changed announcement (id:§a" + id + "§b) to the following: §a" + event.getMessage());
                            }

                            player.performCommand("owlery");
                        }
                    };

                    confirmDeleteGui.show(player);
                }
            }
        }
    }
}
