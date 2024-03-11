package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.PublishMsg;
import no.hvl.dat110.common.TODO;

public class DisplayDevice {

	public static void main(String[] args) {
		Client display = new Client("display", Common.BROKERHOST, Common.BROKERPORT);
		int COUNT = 10; // Adjust based on how many messages you expect to receive

		try {
			display.connect();
			display.createTopic(Common.TEMPTOPIC);
			display.subscribe(Common.TEMPTOPIC);

			for (int i = 0; i < COUNT; i++) {
				Message message = display.receive();  // Receive the Message object

				if (message != null) {
					// Extract the string content from the Message object
					String content = message.toString(); // Replace toString() with the actual method to get the message content
					System.out.println("Temperature received: " + content);
				} else {
					System.out.println("Received null message");
				}
			}

			display.unsubscribe(Common.TEMPTOPIC);
			display.disconnect();

		} catch (Exception e) {
			System.err.println("Error in DisplayDevice: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (display.isAlive()) {
				display.disconnect();
			}
		}
	}
}
