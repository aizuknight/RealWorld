package me.aizuknight.realworld.tasks;

import me.aizuknight.realworld.RealWorld;
import me.aizuknight.realworld.modules.AbstractTemperatureHumiditySensor;
import me.aizuknight.realworld.modules.python.Dht11;
import me.aizuknight.realworld.scoreboard.PiScoreboardManager;
import org.bukkit.ChatColor;

import java.util.AbstractMap;
import java.util.Map;

public class TemperatureHumiditySensorTask extends AbstractTask {
    private final AbstractTemperatureHumiditySensor temperatureHumiditySensor;

    public TemperatureHumiditySensorTask(int pin, long interval) {
        super(interval);
        temperatureHumiditySensor = new Dht11(pin);
    }

    @Override
    public void run() {
        new Thread(() -> {
            double thi = temperatureHumiditySensor.getTHI();
            if (thi == -1) return;
            Map.Entry<String, String> thiEntry = evaluate(thi);
            PiScoreboardManager scoreboardManager = RealWorld.getScoreboardManager();
            scoreboardManager.updateThi(thiEntry);
            logger.info("THI: " + thi);
        }).start();
    }

    private Map.Entry<String, String> evaluate(double thi) {
        ChatColor color;
        String message;
        if(thi < 71) { color = ChatColor.GREEN; message = color + "Cool"; }
        else if(72 <= thi && thi < 80) { color = ChatColor.YELLOW; message = color + "Mild"; }
        else if(80 <= thi && thi < 85) { color = ChatColor.GOLD; message = color + "Moderate"; }
        else if(85 <= thi && thi < 90) { color = ChatColor.RED; message = color + "Sweaty"; }
        else {
            color = ChatColor.DARK_RED; message = color + "Severe";
//            BroadcastUtilities.makeDesert();
        }
        return new AbstractMap.SimpleEntry<>(color + String.format("%.2f", thi), message);
    }
}
