package fr.naruse.hub.main;

import fr.naruse.hub.event.HubListeners;
import fr.naruse.hub.utils.Utils;
import fr.naruse.servermanager.core.ServerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class HubPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new HubListeners(), this);

        ServerManager.get().getCurrentServer().getData().addStatus(Utils.HUB_STATUS);
    }

}
