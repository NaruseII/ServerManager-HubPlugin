package fr.naruse.hub.utils;

import fr.naruse.servermanager.core.server.Server;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {

    public static final ItemStack HUB_ITEM = new ItemStack(Material.COMPASS);
    public static final Server.Status HUB_STATUS = Server.Status.registerNewStatus("HUB");

    static {
        ItemMeta meta = HUB_ITEM.getItemMeta();
        meta.setDisplayName("§aHubs list");
        HUB_ITEM.setItemMeta(meta);
    }

}
