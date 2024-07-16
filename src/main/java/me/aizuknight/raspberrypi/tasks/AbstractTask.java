package me.aizuknight.raspberrypi.tasks;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Logger;

public abstract class AbstractTask extends BukkitRunnable {
    private final long interval;
    protected final Logger logger = Bukkit.getLogger();

    public AbstractTask(long interval) {
        this.interval = interval;
    }

    public long getInterval() {
        return interval;
    }
}
