package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.PublishMsg;
import no.hvl.dat110.common.TODO;

public class DisplayDevice {

	private static final int COUNT = 10;

	public static void main (String[] args) {

		System.out.println("Display starting ...");
		// create a client object and use it to
		// - connect to the broker - use "display" as the username
		// - create the temperature topic on the broker
		// - subscribe to the topic
		// - receive messages on the topic
		// - unsubscribe from the topic
		// - disconnect from the broker

		// setup display
		String user = "display";
		String broker = Common.BROKERHOST;
		int port = Common.BROKERPORT;

		//Client object
		Client displayClient = new Client(user, broker, port);

		//Connect to broker
		displayClient.connect();

		//Create and sub to temp topic
		String topic = Common.TEMPTOPIC;
		displayClient.createTopic(topic);
		displayClient.subscribe(topic);

		//Recieve message
		for (int i = 0; i < COUNT; i++) {
			String message = displayClient.receive().toString();
			System.out.println("Recieved temperature: " + message);
		}
		//Unsub
		displayClient.unsubscribe(topic);

		//Disconnect
		displayClient.disconnect();


		// TODO - END

		System.out.println("Display stopping ... ");

		throw new UnsupportedOperationException(TODO.method());

	}
}
