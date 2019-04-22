package net.blocklords.freunde.command;

import java.util.ArrayList;
import java.util.List;

import net.blocklords.freunde.database.FriendManager;
import net.blocklords.freunde.database.PlayerInfoManager;
import net.blocklords.freunde.lang.MessageHandler;
import net.blocklords.freunde.utils.InventoryCreator;
import net.blocklords.freunde.utils.ItemCreator;
import net.blocklords.freunde.utils.SkullAPI;
import net.blocklords.freunde.utils.SkullAPIoff;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Friend implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if ((sender instanceof Player)) {
            Player player = (Player) sender;
            Inventory inv = InventoryCreator.createInventory(MessageHandler.getMessage("INVENTORY_FRIENDS"), 54);
            List<String> friends = FriendManager.getAllFriends(player);
            //List<String> lore = new ArrayList();
            for (int i = 0; i < friends.size(); i++) {
                if (i == 54) {
                    break;
                }
                OfflinePlayer op = Bukkit.getOfflinePlayer((String) friends.get(i));
                if (PlayerInfoManager.isOnline(op.getName())) {
                    inv.setItem(i,(new SkullAPI("§2"+ op.getName(),op.getName(),1,"§2Online").buildSkull()));
                    /*ItemCreator itemc = new ItemCreator(Material.SKULL_ITEM, op.getName(), 1, lore, (byte)3);
                    ItemStack item = itemc.create();
                    inv.setItem(i, item);*/

                } else {
                    inv.setItem(i,(new SkullAPIoff("§4"+ op.getName(),op.getName(),1,"§4Offline").buildSkull()));

                   /* ItemCreator itemc = new ItemCreator(Material.SKULL_ITEM, "§4" + op.getName(), 1, "§4Offline", (byte) 0);
                    ItemStack item = itemc.create();
                    inv.setItem(i, item);*/
                }
            }
            player.openInventory(inv);
            return true;
        }
        sender.sendMessage(MessageHandler.getMessage("NO_PLAYER"));
        return true;
    }
}
