package net.blocklords.freunde.database;

import java.sql.ResultSet;

public class PlayerInfoManager {
    public static String getUUID(String player)
    {
        try
        {
            ResultSet rs = MySQL.getResult("SELECT * FROM PlayerInfo WHERE Playername = '" + player + "'");
            if (rs.next()) {
                return rs.getString("PlayerUUID");
            }
        }
        catch (Exception ex)
        {
            MySQL.connect();
            return getUUID(player);
        }
        return "";
    }

    public static String getName(String UUID)
    {
        try
        {
            ResultSet rs = MySQL.getResult("SELECT * FROM PlayerInfo WHERE PlayerUUID = '" + UUID + "'");
            if (rs.next()) {
                return rs.getString("Playername");
            }
        }
        catch (Exception ex)
        {
            MySQL.connect();
            return getName(UUID);
        }
        return "";
    }

    public static boolean isOnline(String player)
    {
        try
        {
            ResultSet rs = MySQL.getResult("SELECT * FROM PlayerInfo WHERE Playername = '" + player + "'");
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
            return isOnline(player);
        }
        return false;
    }

    public static void setOnline(String player, boolean online)
    {
        try
        {
            int set = 0;
            if (online) {
                set = 1;
            }
            MySQL.update("UPDATE PlayerInfo SET isOnline = '" + set + "' WHERE Playername = '" + player + "'");
        }
        catch (Exception ex)
        {
            MySQL.connect();
            setOnline(player, online);
        }
    }

    public static void setServer(String player, String server)
    {
        try
        {
            MySQL.update("UPDATE PlayerInfo SET Server = '" + server + "' WHERE Playername = '" + player + "'");
        }
        catch (Exception ex)
        {
            MySQL.connect();
            setServer(player, server);
        }
    }

    public static String getServer(String player)
    {
        String server = "";
        try
        {
            ResultSet rs = MySQL.getResult("SELECT * FROM PlayerInfo WHERE Playername = '" + player + "'");
            if (rs.next()) {
                return rs.getString("Server");
            }
        }
        catch (Exception localException) {}
        return server;
    }
}
