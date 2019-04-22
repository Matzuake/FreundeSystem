package net.blocklords.freunde.interfaces;

import net.blocklords.freunde.database.MySQL;

import java.sql.ResultSet;
import java.util.List;

import net.blocklords.freunde.enums.EnumSetting;
import org.bukkit.entity.Player;

public abstract class User
        implements IUser
{
    public void addFriend(User player)
    {
        try
        {
            MySQL.update("INSERT INTO Friends (Playername, PlayerUUID, Friendname, FriendUUID) VALUES ('" + player.getName() + "', '" + player.getUUID() + "', '" + getName() + "', '" + getUUID() + "')");
            MySQL.update("INSERT INTO Friends (Playername, PlayerUUID, Friendname, FriendUUID) VALUES ('" + getName() + "', '" + getUUID() + "', '" + player.getName() + "', '" + player.getUUID() + "')");
        }
        catch (Exception ex)
        {
            MySQL.connect();
            addFriend(player);
        }
    }

    public String getUserName()
    {
        try
        {
            ResultSet rs = MySQL.getResult("SELECT * FROM PlayerInfo WHERE PlayerUUID = '" + getUUID() + "'");
            if (rs.next()) {
                return rs.getString("Playername");
            }
            return "§4Can't find Username";
        }
        catch (Exception ex)
        {
            MySQL.connect();
        }
        return getUserName();
    }

    public String getUUID()
    {
        try
        {
            ResultSet rs = MySQL.getResult("SELECT * FROM PlayerInfo WHERE Playername = '" + getName() + "'");
            if (rs.next()) {
                return rs.getString("PlayerUUID");
            }
            return "§4Can't find UUID!";
        }
        catch (Exception ex)
        {
            MySQL.connect();
        }
        return getUserName();
    }

    public boolean isOnline()
    {
        try
        {
            ResultSet rs = MySQL.getResult("SELECT * FROM PlayerInfo WHERE Playername = '" + getName() + "'");
            if (rs.next())
            {
                int online = rs.getInt("isOnline");
                if (online == 0) {
                    return false;
                }
                return true;
            }
        }
        catch (Exception ex)
        {
            MySQL.connect();
            return isOnline();
        }
        return false;
    }

    public String getServerName()
    {
        try
        {
            ResultSet rs = MySQL.getResult("SELECT * FROM PlayerInfo WHERE PlayerUUID = '" + getUUID() + "'");
            if (rs.next()) {
                return rs.getString("Server");
            }
        }
        catch (Exception ex)
        {
            MySQL.connect();
            return getServerName();
        }
        return "§4Can't find Server";
    }

    public void setOnline(boolean online)
    {
        try
        {
            int set = 0;
            if (online) {
                set = 1;
            }
            MySQL.update("UPDATE PlayerInfo SET isOnline = '" + set + "' WHERE PlayerUUID = '" + getUUID() + "'");
        }
        catch (Exception ex)
        {
            MySQL.connect();
            setOnline(online);
        }
    }

    public void sendInvite(User player)
    {
        try
        {
            String puuid = getUUID();
            String suuid = player.getUUID();

            MySQL.update("INSERT INTO Invite (PlayerUUID, FriendUUID) VALUES ('" + puuid + "', '" + suuid + "')");
        }
        catch (Exception ex)
        {
            MySQL.connect();
            sendInvite(player);
        }
    }

    public void removeInvite(User player)
    {
        try
        {
            String puuid = getUUID();
            String suuid = player.getUUID();

            MySQL.update("DELETE FROM Invite WHERE PlayerUUID = '" + puuid + "' AND FriendUUID = '" + suuid + "'");
        }
        catch (Exception ex)
        {
            MySQL.connect();
            removeInvite(player);
        }
    }

    public void removeFriend(User player)
    {
        try
        {
            MySQL.update("DELETE FROM Friends WHERE Playername = '" + getName() + "' AND Friendname = '" + player.getName() + "'");
            MySQL.update("DELETE FROM Friends WHERE Friendname = '" + getName() + "' AND Playername = '" + player.getName() + "'");
        }
        catch (Exception ex)
        {
            MySQL.connect();
            removeFriend(player);
        }
    }

    public List<Player> getFriends()
    {
        return null;
    }

    public List<Player> getInvites()
    {
        return null;
    }

    public void setSetting(EnumSetting setting, boolean settingvalue) {}

    public boolean isSetting(EnumSetting setting)
    {
        return false;
    }
}
