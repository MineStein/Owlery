package me.tylergrissom.godricsowlery.listener;

import me.tylergrissom.godricsowlery.Main;
import me.tylergrissom.godricsowlery.menu.MenuGUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * Copyright Tyler Grissom 2017
 */
public class InventoryListener implements Listener {

    private Main plugin;

    public Main getPlugin() {
        return plugin;
    }

    public InventoryListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        MenuGUI menu = MenuGUI.checkForMenuClose(plugin, event); //Assuming that main is a JavaPlugin object, your main class instance
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        MenuGUI menu = MenuGUI.checkForMenuClick(plugin, event, true); //Assuming that main is a JavaPlugin object, your main class instance
        if (menu != null) {
            event.setCancelled(true);
        }
    }
}
