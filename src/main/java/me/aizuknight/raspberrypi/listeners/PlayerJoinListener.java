package me.aizuknight.raspberrypi.listeners;

import me.aizuknight.raspberrypi.RaspberryPi;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        RaspberryPi.getScoreboardManager().set(event.getPlayer());
    }
}
