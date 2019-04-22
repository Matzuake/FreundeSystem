package net.blocklords.freunde.database;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class FriendManager {
    public static List<String> getAllFriends(OfflinePlayer player)
    {
        List<String> friends = new ArrayList();
        try
        {
            ResultSet rs = MySQL.getResult("SELECT * FROM Friends WHERE Playername = '" + player.getName() + "'");
            while (rs.next()) {
                friends.add(rs.getString("Friendname"));
            }
        }
        catch (Exception ex)
        {
            MySQL.connect();
            return getAllFriends(player);
        }
        return friends;
    }

    public static void addFriend(OfflinePlayer player, OfflinePlayer friend)
    {
        try
        {
            MySQL.update("INSERT INTO Friends (Playername, PlayerUUID, Friendname, FriendUUID) VALUES ('" + player.getName() + "', '" + player.getUniqueId().toString() + "', '" + friend.getName() + "', '" + friend.getUniqueId().toString() + "')");
            MySQL.update("INSERT INTO Friends (Playername, PlayerUUID, Friendname, FriendUUID) VALUES ('" + friend.getName() + "', '" + friend.getUniqueId().toString() + "', '" + player.getName() + "', '" + player.getUniqueId().toString() + "')");
        }
        catch (Exception ex)
        {
            MySQL.connect();
            addFriend(player, friend);
        }
    }

    public static void removeFriend(Player player, OfflinePlayer friend)
    {
        try
        {
            MySQL.update("DELETE FROM Friends WHERE Playername = '" + player.getName() + "' AND Friendname = '" + friend.getName() + "'");
            MySQL.update("DELETE FROM Friends WHERE Friendname = '" + player.getName() + "' AND Playername = '" + friend.getName() + "'");
        }
        catch (Exception ex)
        {
            MySQL.connect();
            removeFriend(player, friend);
        }
    }

    public static boolean isFriend(Player player, OfflinePlayer player2)
    {
        try
        {
            ResultSet rs = MySQL.getResult("SELECT * FROM Friends WHERE Playername = '" + player.getName() + "'");
            while (rs.next())
            {
                String friendname = rs.getString("Friendname");
                if (player2.getName().equalsIgnoreCase(friendname)) {
                    return true;
                }
            }
        }
        catch (Exception ex)
        {
            MySQL.connect();
            return isFriend(player, player2);
        }
        return false;
    }

    public static int getFriends(OfflinePlayer player)
    {
        return getAllFriends(player).size();
    }
}
