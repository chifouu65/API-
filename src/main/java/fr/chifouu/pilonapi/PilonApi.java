package fr.chifouu.pilonapi;

import fr.chifouu.pilonapi.listeners.player.PlayerJoinListeners;
import fr.chifouu.pilonapi.mysql.DataBaseManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PilonApi extends JavaPlugin {

    public static PilonApi INSTANCE;
    public DataBaseManager database;

    @Override
    public void onEnable() {
        INSTANCE = this;

        //DATABASE
        database = new DataBaseManager("jdbc://mysql:", "localhost", "dev", "root", "chifouu");
        database.connexion();

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoinListeners(),this);
        //
    }

    @Override
    public void onDisable() {
        database.deconnexion();
    }
}
