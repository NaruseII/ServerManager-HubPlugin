package fr.naruse.hub.inventory;

import fr.naruse.servermanager.core.config.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class AbstractInventory implements Listener {

    protected JavaPlugin pl;
    protected Player p;
    private boolean isDone = false;
    protected Inventory inventory;
    protected Configuration configuration;

    public AbstractInventory(JavaPlugin pl, Player p, String invName, int size) {
        this(pl, p, invName, size, true);
    }
    public AbstractInventory(JavaPlugin pl, Player p, String invName, int size, boolean initInventory) {
        this.pl = pl;
        this.p = p;
        this.inventory = Bukkit.createInventory(null, size, invName);
        Bukkit.getPluginManager().registerEvents(this, pl);
        if(initInventory){
            this.inventory = Bukkit.createInventory(null, size, invName);
            Bukkit.getPluginManager().registerEvents(this, pl);
            initInventory(inventory);
            p.openInventory(inventory);
        }
    }

    protected abstract void initInventory(Inventory inventory);

    protected abstract void actionPerformed(Player p, ItemStack item, InventoryAction action, int slot);

    public void onClose() { }

    @EventHandler
    public void onClickEvent(InventoryClickEvent e){
        if(isDone){
            return;
        }
        if(!(e.getWhoClicked() instanceof Player)){
            return;
        }
        Player p = (Player) e.getWhoClicked();
        if(p != this.p){
            return;
        }
        if(!e.getInventory().equals(inventory)){
            return;
        }
        if(e.getCurrentItem() == null){
            return;
        }
        e.setCancelled(true);
        actionPerformed(p, e.getCurrentItem(), e.getAction(), e.getSlot());
    }

    @EventHandler
    public void onCloseEvent(InventoryCloseEvent e){
        Player p = (Player) e.getPlayer();
        if(p != this.p){
            return;
        }
        if(!e.getInventory().equals(inventory)){
            return;
        }
        onClose();
        isDone = true;
    }

    @EventHandler
    public void openEvent(InventoryOpenEvent e){
        Player p = (Player) e.getPlayer();
        if(p != this.p){
            return;
        }
        if(!e.getInventory().equals(inventory)){
            return;
        }
        isDone = false;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }
}