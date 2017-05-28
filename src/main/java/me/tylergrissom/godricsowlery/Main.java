package me.tylergrissom.godricsowlery;

import me.tylergrissom.godricsowlery.chat.ChatTracker;
import me.tylergrissom.godricsowlery.command.OwleryCommand;
import me.tylergrissom.godricsowlery.listener.ChatListener;
import me.tylergrissom.godricsowlery.listener.InventoryListener;
import me.tylergrissom.godricsowlery.task.AnnouncementTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Copyright Tyler Grissom 2017
 */
public class Main extends JavaPlugin {

    private Main plugin;
    private ChatTracker chatTracker;

    public Main getPlugin() {
        return plugin;
    }

    public ChatTracker getChatTracker() {
        return chatTracker;
    }

    @Override
    public void onEnable() {
        plugin = this;
        chatTracker = new ChatTracker(this);

        getCommand("owlery").setExecutor(new OwleryCommand(this));

        Bukkit.getPluginManager().registerEvents(new InventoryListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(this), this);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new AnnouncementTask(this), 20, getConfig().getInt("announcement-settings.interval"));
    }
}
