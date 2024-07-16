package me.aizuknight.raspberrypi.modules.python;

import me.aizuknight.raspberrypi.modules.AbstractDistanceSensor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Hcsr04 extends PythonModule implements AbstractDistanceSensor {

    public Hcsr04(int triggerPin, int echoPin) {
        command = "python3 /home/aizuknight/python/hcsr04/hcsr04.py " + triggerPin + " " + echoPin;
        processBuilder = new ProcessBuilder("bash", "-c", command);
    }

    @Override
    public double getDistance() {
        try {
            Process process = processBuilder.start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            int exitCode = process.waitFor();
            double distance = -1;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                distance = Double.parseDouble(line);
            }
            bufferedReader.close();
            if(exitCode != 0) logger.info("failure with HC-SR04 module");
            return distance;
        } catch(Exception exception) {
            logger.severe(exception.getMessage() + " in python.Hcsr04");
            return -1;
        }
    }
}
