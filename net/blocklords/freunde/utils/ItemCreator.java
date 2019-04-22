package net.blocklords.freunde.utils;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemCreator {
    String name = "";
    Material mat = Material.AIR;
    SkullMeta smeta ;
    int anzahl = 1;
    byte meta = 0;
    List<String> lore = new ArrayList();

    public ItemStack create()
    {
        ItemStack item = new ItemStack(this.mat, this.anzahl, this.meta);
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        //ItemMeta imeta = item.getItemMeta();
        skullMeta.setDisplayName(this.name);
        skullMeta.setLore(this.lore);
        item.setItemMeta(skullMeta);
        return item;
    }

    public ItemCreator(Material mat, String name)
    {
        this.name = name;
        this.mat = mat;
        this.anzahl = 1;
        this.lore = new ArrayList();
        this.meta = 0;
    }

    public ItemCreator(Material mat, String name, int anzahl)
    {
        this.name = name;
        this.mat = mat;
        this.anzahl = anzahl;
        this.lore = new ArrayList();
        this.meta = 0;
    }

    public static void ItemKopf(Player p, Material mat, int use, int sh, List<String> lore){
        ItemStack i = new ItemStack(mat, use, (short) sh);
        SkullMeta im = (SkullMeta) i.getItemMeta();
        im.setOwner(p.getName());
        i.setItemMeta(im);
        p.getInventory().addItem(i);
    }

    public ItemCreator(Material mat, String name, int anzahl, List<String> lore, byte meta)
    {
        this.name = name;
        this.mat = mat;
        this.anzahl = anzahl;
        this.lore = lore;
        this.meta = meta;
    }

    public ItemCreator(Player p , Material mat, String name, int anzahl, List<String> lore, byte meta)
    {
        this.name = name;
        this.mat = mat;
        this.anzahl = anzahl;
        this.lore = lore;
        this.meta = meta;
    }
}
