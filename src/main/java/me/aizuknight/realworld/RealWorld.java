package me.aizuknight.realworld;

import me.aizuknight.realworld.commands.GpioCommand;
import me.aizuknight.realworld.commands.MessageCommand;
import me.aizuknight.realworld.listeners.PlayerJoinListener;
import me.aizuknight.realworld.scoreboard.PiScoreboardManager;
import me.aizuknight.realworld.tasks.TemperatureHumiditySensorTask;
import me.aizuknight.realworld.tasks.AbstractTask;
import me.aizuknight.realworld.tasks.DistanceSensorTask;
import org.bukkit.plugin.java.JavaPlugin;

public final class RealWorld extends JavaPlugin {
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
