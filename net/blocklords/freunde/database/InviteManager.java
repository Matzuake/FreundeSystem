package net.blocklords.freunde.database;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InviteManager {
    public static void addRequest(String player, String sendto)
    {
        try
        {
            String puuid = PlayerInfoManager.getUUID(player);
            String suuid = PlayerInfoManager.getUUID(sendto);

            MySQL.update("INSERT INTO Invite (PlayerUUID, FriendUUID) VALUES ('" + puuid + "', '" + suuid + "')");
        }
        catch (Exception ex)
        {
            MySQL.connect();
            addRequest(player, sendto);
        }
    }

    public static boolean sendedRequest(String player, String sendto)
    {
        try
        {
            String puuid = PlayerInfoManager.getUUID(player);
            String suuid = PlayerInfoManager.getUUID(sendto);

            ResultSet rs = MySQL.getResult("SELECT * FROM Invite WHERE PlayerUUID = '" + puuid + "' AND FriendUUID = '" + suuid + "'");
            if (rs.next()) {
                return true;
            }
            return false;
        }
        catch (Exception ex)
        {
            MySQL.connect();
        }
        return sendedRequest(player, sendto);
    }

    public static void removeRequest(String name, String sendto)
    {
        try
        {
            String puuid = PlayerInfoManager.getUUID(name);
            String suuid = PlayerInfoManager.getUUID(sendto);

            MySQL.update("DELETE FROM Invite WHERE PlayerUUID = '" + puuid + "' AND FriendUUID = '" + suuid + "'");
        }
        catch (Exception ex)
        {
            MySQL.connect();
            removeRequest(name, sendto);
        }
    }

    public static List<String> getAllRequests(String name)
    {
        List<String> requests = new ArrayList();
        try
        {
            String puuid = PlayerInfoManager.getUUID(name);

            ResultSet rs = MySQL.getResult("SELECT * FROM Invite WHERE PlayerUUID = '" + puuid + "'");
            while (rs.next())
            {
                String suuid = rs.getString("FriendUUID");
                String sname = PlayerInfoManager.getName(puuid);
                requests.add(sname);
            }
        }
        catch (Exception ex)
        {
            MySQL.connect();
            return getAllRequests(name);
        }
        return requests;
    }

    public static int getRequests(String name)
    {
        return getAllRequests(name).size();
    }
}
