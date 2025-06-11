package com.example.tuya;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TemperatureControllerTest {

    private static class MockTuyaClient implements TemperatureController.TuyaClient {
        double temp1;
        double temp2;
        boolean closed = false;
        @Override
        public double getTemperature(String deviceId) {
            if (deviceId.equals("sensor1")) {
                return temp1;
            } else if (deviceId.equals("sensor2")) {
                return temp2;
            }
            return 0.0;
        }
        @Override
        public void closeShutters(String deviceId) {
            closed = true;
        }
    }

    @Test
    void closesShuttersWhenFirstTempHigher() {
        MockTuyaClient client = new MockTuyaClient();
        client.temp1 = 25.0;
        client.temp2 = 20.0;
        TemperatureController controller = new TemperatureController(client, "sensor1", "sensor2", "shutter1");
        controller.checkAndClose();
        assertTrue(client.closed, "Shutters should close when first sensor is hotter");
    }

    @Test
    void doesNotCloseShuttersWhenFirstTempLower() {
        MockTuyaClient client = new MockTuyaClient();
        client.temp1 = 18.0;
        client.temp2 = 20.0;
        TemperatureController controller = new TemperatureController(client, "sensor1", "sensor2", "shutter1");
        controller.checkAndClose();
        assertFalse(client.closed, "Shutters should stay open when first sensor is cooler");
    }
}
