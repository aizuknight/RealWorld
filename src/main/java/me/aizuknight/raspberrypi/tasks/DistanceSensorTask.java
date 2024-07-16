package me.aizuknight.raspberrypi.tasks;

import me.aizuknight.raspberrypi.RaspberryPi;
import me.aizuknight.raspberrypi.Utilities.BroadcastUtilities;
import me.aizuknight.raspberrypi.modules.AbstractDistanceSensor;
import me.aizuknight.raspberrypi.modules.python.Hcsr04;
import me.aizuknight.raspberrypi.scoreboard.PiScoreboardManager;
import org.bukkit.ChatColor;

import java.util.AbstractMap;
import java.util.Map;

public class DistanceSensorTask extends AbstractTask {
    private final AbstractDistanceSensor distanceSensor;

    public DistanceSensorTask(int triggerPin, int echoPin, long interval) {
        super(interval);
        distanceSensor = new Hcsr04(triggerPin, echoPin);
    }

    @Override
    public void run() {
        new Thread(() -> {
            double distance = distanceSensor.getDistance();
            Map.Entry<String, String> distanceEntry = evaluate(distance);
            PiScoreboardManager scoreboardManager = RaspberryPi.getScoreboardManager();
            scoreboardManager.updateDistance(distanceEntry);
            if(distance == -1.0) return;
            logger.info("Distance: " + distance + "cm");
        }).start();
    }

    private Map.Entry<String, String> evaluate(double distance) {
        if(distance == -1) return new AbstractMap.SimpleEntry<>(ChatColor.GRAY + "? cm", "");
        ChatColor color;
        String message;
        if(distance < 10) {
            color = ChatColor.DARK_RED; message = color + "TOO NEAR";
            BroadcastUtilities.broadcastTitle(ChatColor.DARK_RED + "Something approaching to the server", "", 5, 10, 5);
//            BroadcastUtilities.addFear();
        }
        else if(10 <= distance && distance < 30) { color = ChatColor.RED; message = color + "Pretty Near"; }
        else if(30 <= distance && distance < 70) { color = ChatColor.GOLD; message = color + "Near"; }
        else if(70 <= distance && distance < 100) { color = ChatColor.YELLOW; message = color + "Normal"; }
        else if(100 <= distance && distance < 200) { color = ChatColor.GREEN; message = color + "Far"; }
        else {
            return new AbstractMap.SimpleEntry<>(ChatColor.GREEN + "Far Enough", "");
        }
        return new AbstractMap.SimpleEntry<>(color + String.format("%.2f", distance) + " " + ChatColor.GRAY + "cm", message);
    }
}
