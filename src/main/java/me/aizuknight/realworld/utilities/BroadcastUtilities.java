package me.aizuknight.realworld.utilities;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BroadcastUtilities {
    public static void broadcastTitle(String title, String subtitle, int in, int stay, int out) {
        for(Player player : Bukkit.getOnlinePlayers()) player.sendTitle(title, subtitle, in, stay, out);
    }
}
