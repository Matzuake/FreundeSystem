package net.blocklords.freunde.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class SkullAPIoff {

    ItemStack itemStack;
    ItemMeta itemMeta;
    SkullMeta skullMeta;

    @SuppressWarnings("deprecation")
    public SkullAPIoff (String displayname, String skullOwner, int amount, String lore){
        itemStack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 0);
        skullMeta =(SkullMeta) itemStack.getItemMeta();
        skullMeta.setDisplayName(displayname);
        skullMeta.setOwner(skullOwner);
        ArrayList<String> list = new ArrayList<String>();
        list.add(lore);
        skullMeta.setLore(list);
    }


    public ItemStack buildSkull(){
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }
}
