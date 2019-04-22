package net.blocklords.freunde;

import net.blocklords.freunde.command.Friend;
import net.blocklords.freunde.database.MySQL;
import net.blocklords.freunde.interfaces.User;
import net.blocklords.freunde.listener.InventoryClickListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static String lang;

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        createConfigFile();
        for (Player all : Bukkit.getOnlinePlayers()) {
            User localUser = (User)all;
        }
        getCommand("friends").setExecutor(new Friend());
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(this), this);
    }

    public void createConfigFile()
    {
        if (!getConfig().contains("MySQL.Host"))
        {
            getConfig().set("MySQL.Host", "HOST");
            getConfig().set("MySQL.User", "USER");
            getConfig().set("MySQL.Password", "PASSWORD");
            getConfig().set("MySQL.Database", "DATABASE");
            getConfig().set("MySQL.Port", Integer.valueOf(3306));
            getConfig().set("lang", "English");
            saveConfig();
            reloadConfig();
        }
        MySQL.port = getConfig().getInt("MySQL.Port");
        MySQL.host = getConfig().getString("MySQL.Host");
        MySQL.username = getConfig().getString("MySQL.User");
        MySQL.password = getConfig().getString("MySQL.Password");
        MySQL.database = getConfig().getString("MySQL.Database");
        MySQL.connect();
        lang = getConfig().getString("lang");
    }
}
