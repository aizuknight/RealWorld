package me.aizuknight.raspberrypi;

import me.aizuknight.raspberrypi.commands.GpioCommand;
import me.aizuknight.raspberrypi.commands.MessageCommand;
import me.aizuknight.raspberrypi.listeners.PlayerJoinListener;
import me.aizuknight.raspberrypi.scoreboard.PiScoreboardManager;
import me.aizuknight.raspberrypi.tasks.TemperatureHumiditySensorTask;
import me.aizuknight.raspberrypi.tasks.AbstractTask;
import me.aizuknight.raspberrypi.tasks.DistanceSensorTask;
import org.bukkit.plugin.java.JavaPlugin;

public final class RaspberryPi extends JavaPlugin {
    private static PiScoreboardManager scoreboardManager;
    private static AbstractTask[] tasks;

    @Override
    public void onEnable() {
        // Plugin startup logic

        scoreboardManager = new PiScoreboardManager();

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

        getCommand("message").setExecutor(new MessageCommand());
        getCommand("gpio").setExecutor(new GpioCommand());

        tasks = new AbstractTask[] {
                new TemperatureHumiditySensorTask(16, 200L), // BOARD: 16
                new DistanceSensorTask(38, 40, 40L), // BOARD: 38/40
        };
        for(AbstractTask task : tasks) {
            task.runTaskTimer(this, 0L, task.getInterval());
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for(AbstractTask task : tasks) {
            task.cancel();
        }
    }

    public static PiScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }
}
