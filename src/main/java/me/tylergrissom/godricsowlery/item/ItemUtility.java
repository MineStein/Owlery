package me.tylergrissom.godricsowlery.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Copyright Tyler Grissom 2017
 */
public class ItemUtility {

    public static boolean isSimilar(ItemStack is, Material type, String name) {
        return is.getType().equals(type) && ChatColor.stripColor(is.getItemMeta().getDisplayName()).equalsIgnoreCase(ChatColor.stripColor(name));
    }

    public static boolean isSimilar(ItemStack is, Material type) {
        return is.getType().equals(type);
    }
}
