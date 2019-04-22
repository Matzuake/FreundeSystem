package net.blocklords.freunde.database;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SettingManager {
    public static boolean canGetRequests(String player)
    {
        ResultSet rs = MySQL.getResult("SELECT * FROM Settings WHERE Playername = '" + player + "'");
        try
        {
            if (rs.next())
            {
                String value = rs.getString("getInvites");
                if (value.equalsIgnoreCase("true")) {
                    return true;
                }
                return false;
            }
        }
        catch (SQLException e)
        {
            MySQL.connect();
            return canGetRequests(player);
        }
        return false;
    }

    public static boolean canSendJoinMessages(String player)
    {
        ResultSet rs = MySQL.getResult("SELECT * FROM Settings WHERE Playername = '" + player + "'");
        try
        {
            if (rs.next())
            {
                String value = rs.getString("getJoinMsg");
                if (value.equalsIgnoreCase("true")) {
                    return true;
                }
                return false;
            }
        }
        catch (SQLException e)
        {
            MySQL.connect();
            return canSendJoinMessages(player);
        }
        return false;
    }

    public static boolean canSendMessages(String player)
    {
        ResultSet rs = MySQL.getResult("SELECT * FROM Settings WHERE Playername = '" + player + "'");
        try
        {
            if (rs.next())
            {
                String value = rs.getString("getMsg");
                if (value.equalsIgnoreCase("true")) {
                    return true;
                }
                return false;
            }
        }
        catch (SQLException e)
        {
            MySQL.connect();
            return canSendMessages(player);
        }
        return false;
    }

    public static void setcanSendJoinMessage(String player, boolean send)
    {
        try
        {
            MySQL.update("UPDATE Settings SET getJoinMsg = '" + send + "' WHERE Playername = '" + player + "'");
        }
        catch (Exception ex)
        {
            MySQL.connect();
            setcanSendJoinMessage(player, send);
        }
    }

    public static void setcanSendMessage(String player, boolean send)
    {
        try
        {
            MySQL.update("UPDATE Settings SET getMsg = '" + send + "' WHERE Playername = '" + player + "'");
        }
        catch (Exception ex)
        {
            MySQL.connect();
            setcanSendMessage(player, send);
        }
    }

    public static void setcanSendRequests(String player, boolean send)
    {
        try
        {
            MySQL.update("UPDATE Settings SET getInvites = '" + send + "' WHERE Playername = '" + player + "'");
        }
        catch (Exception ex)
        {
            MySQL.connect();
            setcanSendRequests(player, send);
        }
    }
}

