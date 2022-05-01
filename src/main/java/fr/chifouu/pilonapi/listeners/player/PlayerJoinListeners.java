package fr.chifouu.pilonapi.listeners.player;

import fr.chifouu.pilonapi.PilonApi;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        PilonApi.INSTANCE.database.createAccount(player.getUniqueId());
    }
}
