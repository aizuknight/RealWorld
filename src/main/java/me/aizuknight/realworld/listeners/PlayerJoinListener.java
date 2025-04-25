package me.aizuknight.realworld.listeners;

import me.aizuknight.realworld.RealWorld;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        RealWorld.getScoreboardManager().set(event.getPlayer());
    }
}
