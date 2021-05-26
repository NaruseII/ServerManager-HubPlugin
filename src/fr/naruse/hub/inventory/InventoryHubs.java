package fr.naruse.hub.inventory;

import com.google.common.collect.Lists;
import fr.naruse.hub.utils.Utils;
import fr.naruse.servermanager.core.CoreServerType;
import fr.naruse.servermanager.core.ServerManager;
import fr.naruse.servermanager.core.connection.packet.PacketSwitchServer;
import fr.naruse.servermanager.core.server.Server;
import fr.naruse.servermanager.core.server.ServerList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class InventoryHubs extends AbstractInventory{
    public InventoryHubs(JavaPlugin pl, Player p) {
        super(pl, p, "§2§lHubs", 6*3);
    }

    @Override
    protected void initInventory(Inventory inventory) {
        ServerList.getAll().stream().filter(server -> server.getCoreServerType().is(CoreServerType.BUKKIT_MANAGER) && server.getData().hasStatus(Utils.HUB_STATUS)).forEach(server -> {
            ItemStack item = new ItemStack(Material.ENDER_PEARL);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ServerManager.get().getCurrentServer().equals(server) ? "§4"+server.getName()+" §5[Current server]" : "§c"+server.getName());
            meta.setLore(Lists.newArrayList("§5Players: §e"+server.getData().getPlayerSize()+"/"+server.getData().getCapacity()));
            item.setItemMeta(meta);
            inventory.addItem(item);
        });
    }

    @Override
    protected void actionPerformed(Player p, ItemStack item, InventoryAction action, int slot) {
        if(item != null){

        }
    }
}
