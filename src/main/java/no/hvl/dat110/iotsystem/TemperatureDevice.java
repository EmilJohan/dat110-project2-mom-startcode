package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.common.TODO;

import java.util.Random;

public class TemperatureDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {
		Client sensor = new Client("sensor", Common.BROKERHOST, Common.BROKERPORT);

		try {
			sensor.connect();

			for (int i = 0; i < COUNT; i++) {
				int temperature = readTemperature();
				sensor.publish(Common.TEMPTOPIC, String.valueOf(temperature));
				Thread.sleep(1000);
			}

			sensor.disconnect();
		} catch (Exception e) {
			System.err.println("Error in TemperatureDevice: " + e.getMessage());
			e.printStackTrace();
		}
	}
	private static int readTemperature() {
		return (new Random().nextInt(30) + 10);
	}
}
