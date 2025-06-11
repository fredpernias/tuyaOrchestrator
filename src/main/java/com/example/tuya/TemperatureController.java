package com.example.tuya;

/**
 * Example program that checks temperature from two Tuya sensors and closes shutters
 * if the first sensor's temperature is higher than the second.
 * This is a simplified example using a hypothetical TuyaClient API.
 */
public class TemperatureController {

    private final TuyaClient client;
    private final String sensor1Id;
    private final String sensor2Id;
    private final String shutterId;

    public TemperatureController(TuyaClient client, String sensor1Id, String sensor2Id, String shutterId) {
        this.client = client;
        this.sensor1Id = sensor1Id;
        this.sensor2Id = sensor2Id;
        this.shutterId = shutterId;
    }

    public void checkAndClose() {
        double temp1 = client.getTemperature(sensor1Id);
        double temp2 = client.getTemperature(sensor2Id);
        if (temp1 > temp2) {
            System.out.println("Temp "+temp1+" > "+temp2+", closing shutters...");
            client.closeShutters(shutterId);
        } else {
            System.out.println("Temp1: "+temp1+", Temp2: "+temp2+" -> no action.");
        }
    }

    // Hypothetical TuyaClient interface
    public interface TuyaClient {
        double getTemperature(String deviceId);
        void closeShutters(String deviceId);
    }

    public static void main(String[] args) {
        // Replace with real implementation of TuyaClient
        TuyaClient client = new TuyaClient() {
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
        controller.checkAndClose();
    }
}
