package me.tylergrissom.godricsowlery.command;

import me.tylergrissom.godricsowlery.Main;
import me.tylergrissom.godricsowlery.item.ItemBuilder;
import me.tylergrissom.godricsowlery.item.ItemUtility;
import me.tylergrissom.godricsowlery.menu.MenuGUI;
import me.tylergrissom.godricsowlery.menu.YesNoCancelGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Copyright Tyler Grissom 2017
 */
public class OwleryCommand extends CommandBase {

    private Main plugin;

    public Main getPlugin() {
        return plugin;
    }

    public OwleryCommand(Main plugin) {
        this.plugin = plugin;
    }

    void execute(CommandSender sender, Command command, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender);
            ItemStack announcements = new ItemBuilder()
                    .type(Material.PAPER)
                    .name("§7Announcements")
                    .lore("§7View and modify", "§8Owlery §7announcements.", "", "§6§lClick §7to open")
                    .build();

            MenuGUI gui = new MenuGUI("Owlery", 9, plugin) {
                @Override
                public void onClose(InventoryCloseEvent e) {

                }

                @Override
                public void onClick(InventoryClickEvent e) {
                    Player p = (Player)e.getWhoClicked();
                    ItemStack is = e.getCurrentItem();

                    if (is == null || is.getItemMeta() == null) return;

                    if (ItemUtility.isSimilar(is, Material.PAPER, "Announcements")) {
                        p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1F, 1F);

                        MenuGUI innerGui = new MenuGUI("Announcements", 54, plugin) {
                            @Override
                            public void onClose(InventoryCloseEvent e) {

                            }

                            @Override
                            public void onClick(InventoryClickEvent e) {
                                final Player player1 = (Player) e.getWhoClicked();
                                ItemStack is1 = e.getCurrentItem();

                                if (is1 == null || is1.getItemMeta() == null) return;

                                if (ItemUtility.isSimilar(is1, Material.PAPER)) {
                                    final int id = Integer.parseInt(is1.getItemMeta().getDisplayName().substring(3)) - 1;
                                    final List<String> announcements = plugin.getConfig().getStringList("announcement-settings.announcements");
                                    ClickType click = e.getClick();

                                    if (click.equals(ClickType.SHIFT_LEFT) || click.equals(ClickType.SHIFT_RIGHT)) {
                                        YesNoCancelGUI confirmDeleteGui = new YesNoCancelGUI("Confirm Deletion", plugin) {
                                            @Override
                                            public void onFinish(Response response) {
                                                if (response.equals(Response.YES)) {
                                                    announcements.remove(id);

                                                    plugin.getConfig().set("announcement-settings.announcements", announcements);
                                                    plugin.saveConfig();

                                                    player1.closeInventory();
                                                    player1.playSound(player1.getLocation(), Sound.UI_BUTTON_CLICK, 1F, 1F);
                                                }

                                                player1.performCommand("owlery");
                                            }
                                        };

                                        confirmDeleteGui.show(player1);
                                    } else if (click.equals(ClickType.LEFT)) {
                                        String announcement = ChatColor.translateAlternateColorCodes('&', announcements.get(id));

                                        player1.closeInventory();
                                        player1.playSound(player1.getLocation(), Sound.UI_BUTTON_CLICK, 1F, 1F);
                                        player1.sendMessage(announcement);
                                    } else if (click.equals(ClickType.RIGHT)) {
                                        // TODO
                                    }
                                } else if (ItemUtility.isSimilar(is1, Material.INK_SACK)) {

                                }
                            }
                        }.addOption(new ItemBuilder().type(Material.INK_SACK).name("§a§lCreate").lore("§7Create a new announcement", "", "§6§lClick §7to use").data((byte) 10).build(), 53);

                        List<String> announcements = plugin.getConfig().getStringList("announcement-settings.announcements");

                        for (int i = 0; i < announcements.size(); i++) {
                            ItemStack announcementItem = new ItemBuilder().type(Material.PAPER).name("§7#" + (i + 1)).lore("§a§lLeft-click §7to view", "§c§lRight-click §7to modify", "§6§lShift-click §7to delete").build();

                            innerGui.addOption(announcementItem);
                        }

                        innerGui.show(p);
                    }
                }
            }.addOption(announcements, 4);

            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1F, 1F);

            gui.show(player);
        } else {
            sender.sendMessage("§4Only players can use this command.");
        }
    }
}
