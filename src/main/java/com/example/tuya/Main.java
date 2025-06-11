package com.example.tuya;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Main program that periodically checks the temperature and controls the shutters.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Replace with real implementation of TuyaClient
        TemperatureController.TuyaClient client = new TemperatureController.TuyaClient() {
            @Override
            public double getTemperature(String deviceId) {
                // TODO implement API call
                return 0.0;
            }

            @Override
            public void closeShutters(String deviceId) {
                // TODO implement API call
            }
        };

        TemperatureController controller = new TemperatureController(
            client,
            "sensor1",
            "sensor2",
            "shutter1"
        );

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(
            controller::checkAndClose,
            0,
            20,
            TimeUnit.MINUTES
        );

        // Keep the main thread alive
        scheduler.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }
}
