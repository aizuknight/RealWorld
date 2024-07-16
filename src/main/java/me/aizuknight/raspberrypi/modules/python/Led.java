package me.aizuknight.raspberrypi.modules.python;

import me.aizuknight.raspberrypi.modules.AbstractLed;
import org.bukkit.Bukkit;

import java.util.logging.Logger;

public class Led implements AbstractLed {
    private final String command;
    private final ProcessBuilder processBuilder;
    private final Logger logger = Bukkit.getLogger();

    public Led(int pin) {
        command = "python3 /home/aizuknight/python/led/led.py " + pin;
        processBuilder = new ProcessBuilder("bash", "-c", command);
    }

    @Override
    public void light() {
        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            logger.info(command);
            if(exitCode != 0) logger.info("failure with LED module");
        } catch(Exception exception) {
            logger.severe(exception.getMessage() + " in python.Led");
        }
    }
}
