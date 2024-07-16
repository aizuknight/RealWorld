package me.aizuknight.raspberrypi.Utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BashUtilities {

    public static void execute(String bashCommand) {
        ProcessBuilder pb = new ProcessBuilder("bash", "-c", bashCommand);
        new Thread(() -> {
            try {
                Process process = pb.start();
                BufferedReader outputs = new BufferedReader(new InputStreamReader(process.getInputStream()));
                BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                int exitVal = process.waitFor();
                StringBuilder output = new StringBuilder();
                String outputLine;
                while ((outputLine = outputs.readLine()) != null) {
                    output.append(outputLine).append("\n");
                }
                StringBuilder error = new StringBuilder();
                String errorLine;
                while ((errorLine = errors.readLine()) != null) {
                    error.append(errorLine).append("\n");
                }
                if (exitVal == 0) {
                    System.out.println(output);
                } else {
                    System.out.println(error);
                }

            } catch(Exception exception) {
                exception.fillInStackTrace();
            }
        }).start();
    }
}
