package fr.chifouu.pilonapi;

import fr.chifouu.pilonapi.listeners.player.PlayerJoinListeners;
import fr.chifouu.pilonapi.mysql.DatabaseManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PilonApi extends JavaPlugin {

    public static PilonApi INSTANCE;


    @Override
    public void onEnable() {
        INSTANCE = this;

        //DATABASE
        DatabaseManager.initAllDatabaseConnection();

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoinListeners(),this);
        //
    }

    @Override
    public void onDisable() {
        DatabaseManager.closeAllDatabaseConnection();
    }
}
