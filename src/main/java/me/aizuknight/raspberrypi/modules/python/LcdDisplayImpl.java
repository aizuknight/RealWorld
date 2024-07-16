package me.aizuknight.raspberrypi.modules.python;

import me.aizuknight.raspberrypi.modules.AbstractLcdDisplay;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LcdDisplayImpl extends PythonModule implements AbstractLcdDisplay {

    public LcdDisplayImpl() {
        command = "python3 /home/aizuknight/python/lcd/lcd.py ";
    }

    @Override
    public void display(String message) {
        String command = super.command + message;
        processBuilder = new ProcessBuilder("bash", "-c", command);
        try {
            Process process = processBuilder.start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            int exitCode = process.waitFor();
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                output.append(line).append("\n");
            }
            bufferedReader.close();
            if(exitCode == 0) logger.info(output.toString());
            else logger.info("failure with LCD Display module");
        } catch(Exception exception) {
            logger.severe(exception.getMessage() + "in python.LcdDisplayImpl");
        }
    }
}
