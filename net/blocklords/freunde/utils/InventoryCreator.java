package net.blocklords.freunde.utils;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class InventoryCreator {
    public static Inventory createInventory(String name, int size)
    {
        Inventory inv = Bukkit.createInventory(null, size, name);
        return inv;
    }
}
