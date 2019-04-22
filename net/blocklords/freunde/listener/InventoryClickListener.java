package net.blocklords.freunde.listener;

import java.util.ArrayList;
import java.util.List;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.blocklords.freunde.Main;
import net.blocklords.freunde.database.FriendManager;
import net.blocklords.freunde.database.PlayerInfoManager;
import net.blocklords.freunde.database.SettingManager;
import net.blocklords.freunde.lang.MessageHandler;
import net.blocklords.freunde.utils.InventoryCreator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryClickListener implements Listener{
    private Main plugin;

    public InventoryClickListener(Main plugin)
    {
        this.plugin = plugin;
    }

    public static List<String> cancelinvs = new ArrayList();

    @EventHandler
    public void on(InventoryClickEvent e)
    {
        if (e.getInventory().getName().equalsIgnoreCase(MessageHandler.getMessage("INVENTORY_FRIENDS")))
        {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (!e.getCurrentItem().hasItemMeta()) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.SKULL_ITEM)
            {
                Inventory inv = InventoryCreator.createInventory("§5" + e.getCurrentItem().getItemMeta().getDisplayName(), 9);
                String name = e.getCurrentItem().getItemMeta().getDisplayName();
                name = ChatColor.stripColor(name);
                OfflinePlayer player = Bukkit.getOfflinePlayer(name);
                List<String> lore = new ArrayList();
                ItemStack back = createItem(MessageHandler.getMessage("ITEM_BACK"), 1, lore, Material.IRON_DOOR);
                ItemStack remove = createItem(MessageHandler.getMessage("ITEM_REMOVE"), 1, lore, Material.BARRIER);
                String lore1 = MessageHandler.getMessage("LORE_PLAYERINFO_NAME");
                lore1 = lore1.replaceAll("%PLAYER%", ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));
                String lore2 = MessageHandler.getMessage("LORE_PLAYERINFO_ONLINE") + PlayerInfoManager.isOnline(player.getName());
                lore.add(lore1);
                lore.add(lore2);
                ItemStack PlayerInfo = createItem(name, 1, lore, Material.SKULL_ITEM, (byte)3);
                lore.clear();
                inv.setItem(8, back);
                inv.setItem(2, remove);
                inv.setItem(0, PlayerInfo);
                if (PlayerInfoManager.isOnline(player.getName()))
                {
                    ItemStack jump = createItem(MessageHandler.getMessage("ITEM_JUMP"), 1, lore, Material.EYE_OF_ENDER);
                    inv.setItem(4, jump);
                }
                e.getWhoClicked().openInventory(inv);
                if (!cancelinvs.contains("§5" + name)) {
                    cancelinvs.add("§5" + name);
                }
                return;
            }
            if (e.getCurrentItem().getType() == Material.PAPER)
            {
                String name = e.getCurrentItem().getItemMeta().getDisplayName();
                Player player = (Player)e.getWhoClicked();
                if (name.equalsIgnoreCase(MessageHandler.getMessage("ITEM_TOGGLEINV"))) {
                    if (SettingManager.canGetRequests(player.getName()))
                    {
                        SettingManager.setcanSendRequests(player.getName(), true);
                        player.sendMessage(MessageHandler.getMessage("MESSAGE_ALLOWINVITES"));
                    }
                    else
                    {
                        SettingManager.setcanSendRequests(player.getName(), false);
                        player.sendMessage(MessageHandler.getMessage("MESSAGE_DENYINVITES"));
                    }
                }
                if (name.equalsIgnoreCase(MessageHandler.getMessage("ITEM_TOGGLEJOINMSG"))) {
                    if (SettingManager.canSendJoinMessages(player.getName()))
                    {
                        SettingManager.setcanSendJoinMessage(player.getName(), true);
                        player.sendMessage(MessageHandler.getMessage("MESSAGE_ALLOWJOINMSG"));
                    }
                    else
                    {
                        SettingManager.setcanSendJoinMessage(player.getName(), false);
                        player.sendMessage(MessageHandler.getMessage("MESSSAGE_DENYJOINMSG"));
                    }
                }
                if (name.equalsIgnoreCase(MessageHandler.getMessage("ITEM_TOGGLEMSG"))) {
                    if (SettingManager.canSendMessages(player.getName()))
                    {
                        SettingManager.setcanSendMessage(player.getName(), true);
                        player.sendMessage(MessageHandler.getMessage("MESSAGE_ALLOWMSG"));
                    }
                    else
                    {
                        SettingManager.setcanSendMessage(player.getName(), false);
                        player.sendMessage(MessageHandler.getMessage("MESSAGE_DENYMSG"));
                    }
                }
            }
        }
        if (cancelinvs.contains(e.getInventory().getName()))
        {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (!e.getCurrentItem().hasItemMeta()) {
                return;
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(MessageHandler.getMessage("ITEM_BACK")))
            {
                e.getWhoClicked().closeInventory();
                ((Player)e.getWhoClicked()).performCommand("friend");
                return;
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(MessageHandler.getMessage("ITEM_REMOVE")))
            {
                OfflinePlayer op = Bukkit.getOfflinePlayer(ChatColor.stripColor(e.getInventory().getName()));
                FriendManager.removeFriend((Player)e.getWhoClicked(), op);
                String msg = MessageHandler.getMessage("MESSAGE_REMOVE");
                msg = msg.replaceAll("%FRIEND%", op.getName());
                e.getWhoClicked().sendMessage(msg);
                e.getWhoClicked().closeInventory();
                return;
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(MessageHandler.getMessage("ITEM_JUMP")))
            {
                OfflinePlayer op = Bukkit.getOfflinePlayer(ChatColor.stripColor(e.getInventory().getName()));
                String server = PlayerInfoManager.getServer(op.getName());
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF(server);
                ((Player)e.getWhoClicked()).sendPluginMessage(this.plugin, "BungeeCord", out.toByteArray());
            }
        }
    }

    public ItemStack createItem(String displayname, int anzahl, List<String> lore, Material material)
    {
        ItemStack item = new ItemStack(material, anzahl);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createItem(String displayname, int anzahl, List<String> lore, Material material, byte meta)
    {
        ItemStack item = new ItemStack(material, anzahl, meta);
        ItemMeta meta1 = item.getItemMeta();
        meta1.setDisplayName(displayname);
        meta1.setLore(lore);
        item.setItemMeta(meta1);
        return item;
    }
}
