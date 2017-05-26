package me.tylergrissom.godricsowlery.task;

import me.tylergrissom.godricsowlery.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

/**
 * Copyright Tyler Grissom 2017
 */
public class AnnouncementTask extends BukkitRunnable {

    private Main plugin;

    public Main getPlugin() {
        return plugin;
    }

    public AnnouncementTask(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        List<String> announcements = plugin.getConfig().getStringList("announcement-settings.announcements");
        Random random = new Random();
        String announcement = announcements.get(random.nextInt(announcements.size()));
        String format = plugin.getConfig().getString("announcement-settings.format");
        String formattedMessage = ChatColor.translateAlternateColorCodes('&', format.replace("{ANNOUNCEMENT}", announcement));

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(formattedMessage);
        }
    }
}
