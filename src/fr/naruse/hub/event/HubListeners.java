package fr.naruse.hub.event;

import fr.naruse.hub.inventory.InventoryHubs;
import fr.naruse.hub.main.HubPlugin;
import fr.naruse.hub.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class HubListeners implements Listener {

    @EventHandler
    public void join(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.getInventory().setItem(4, Utils.HUB_ITEM);
    }

    @EventHandler
    public void interact(PlayerInteractEvent e){
        Player p = e.getPlayer();

        if(e.getItem() != null && e.getItem().equals(Utils.HUB_ITEM)){
            e.setCancelled(true);
            new InventoryHubs(HubPlugin.getPlugin(HubPlugin.class), p);
        }
    }

    @EventHandler
    public void click(InventoryClickEvent e){
        if(e.getCurrentItem() != null && e.getCurrentItem().equals(Utils.HUB_ITEM)){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void drop(PlayerDropItemEvent e){
        if(e.getItemDrop().getItemStack().equals(Utils.HUB_ITEM)){
            e.setCancelled(true);
        }
    }

}
