package net.blocklords.freunde.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import org.bukkit.OfflinePlayer;

public class MySQL {
    public static String username = "";
    public static String password = "";
    public static String database = "";
    public static String host = "";
    public static int port = 3306;
    public static Connection con;

    public static void connect()
    {
        if (!isConnected()) {
            try
            {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&useSSL=false", username, password);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void close()
    {
        if (isConnected()) {
            try
            {
                con.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static boolean isConnected()
    {
        return con != null;
    }

    public static void createTable()
    {
        if (isConnected()) {
            try
            {
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Friends (Playername TEXT,PlayerUUID TEXT,Friendname TEXT,FriendUUID TEXT,RID int(11) NOT NULL auto_increment,primary KEY (RID));");
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Invite (PlayerUUID TEXT,FriendUUID TEXT,RID int(11) NOT NULL auto_increment,primary KEY (RID));");
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS PlayerInfo (Playername TEXT,PlayerUUID TEXT,isOnline INT(12),Server TEXT, int(11) NOT NULL auto_increment,primary KEY (RID));");
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Settings (Playername TEXT,PlayerUUID TEXT,getMsg INT(12),getJoinMsg INT(12),getInvites INT(12),RID int(11) NOT NULL auto_increment,primary KEY (RID));");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void update(String qry)
    {
        if (!isConnected()) {
            connect();
        }
        try
        {
            con.createStatement().executeUpdate(qry);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static ResultSet getResult(String qry)
    {
        if (!isConnected()) {
            connect();
        }
        try
        {
            return con.createStatement().executeQuery(qry);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isInDatabase(OfflinePlayer player)
    {
        try
        {
            if (!isConnected()) {
                connect();
            }
            ResultSet rs = getResult("SELECT * FROM PlayerInfo WHERE PlayerUUID = '" + player.getUniqueId().toString() + "'");
            if (rs.next()) {
                return true;
            }
            return false;
        }
        catch (Exception localException) {}
        return true;
    }

    public static void addPlayerToDatabase(OfflinePlayer player)
    {
        try
        {
            if (!isConnected()) {
                connect();
            }
            update("INSERT INTO PlayerInfo (Playername, PlayerUUID, isOnline, Server) VALUES ('" + player.getName() + "', '" + player.getUniqueId().toString() + "', '1', 'offline')");
        }
        catch (Exception localException) {}
    }
}
