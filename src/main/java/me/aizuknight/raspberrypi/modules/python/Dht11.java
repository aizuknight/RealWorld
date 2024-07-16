package me.aizuknight.raspberrypi.modules.python;

import me.aizuknight.raspberrypi.modules.AbstractTemperatureHumiditySensor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Dht11 extends PythonModule implements AbstractTemperatureHumiditySensor {
    private final double[] out = new double[2];

    public Dht11(int pin) {
        command = "python3 /home/aizuknight/python/dht11/dht11.py " + pin;
        processBuilder = new ProcessBuilder("bash", "-c", command);
    }

    @Override
    public double getTHI() {
        try {
            Process process = processBuilder.start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            int exitCode = process.waitFor();
            String line;
            for(int i = 0; i < 2; ++i) {
                line = bufferedReader.readLine();
                if(line == null) break;
                // output must be numbers, out[0]: temperature, out[1]: humidity
                out[i] = Double.parseDouble(line);
            }
            bufferedReader.close();
            if(exitCode == 0) logger.info("Temperature: " + out[0] + " °C, " + "Humidity: " + out[1] + " %");
            else {
                logger.info("failure with DHT11 module");
                return -1;
            }
            return 0.81 * out[0] + 0.01 * out[1] * (0.99 * out[0] - 14.3) + 46.3;
        } catch(Exception exception) {
            logger.severe(exception.getMessage() + " in python.Dht11");
            return -1;
        }
    }
}
