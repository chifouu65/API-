package fr.chifouu.pilonapi.listeners.player;

import fr.chifouu.pilonapi.PilonApi;
import fr.chifouu.pilonapi.mysql.DatabaseManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerJoinListeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        createAccount(player);

    }

    private void createAccount(Player player) {

        try {

            PreparedStatement sts = DatabaseManager.DEV.getDatabaseAccess().getConnection().prepareStatement("INSERT INTO players (uuid_player, pseudo_player, coins) VALUES (?,?,?)");
            sts.setString(2, player.getName());
            sts.setString(1, player.getUniqueId().toString());
            sts.setInt(3, 0);
            sts.execute();
            sts.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
