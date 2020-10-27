package com.s216673380;

public class Main {

    public static void main(String[] args) {
	Subscriber subscriber1 = new Subscriber();
	Broker broker = Broker.getInstance();
	subscriber1.register(broker, "wrpv302", "wrr301", "wrev301", "wrdb301", "wuiv301");
	subscriber1.register(broker, "xyz302");
	subscriber1.deregister(broker, "wrpv302");
	Publisher publisher = new Publisher();
	Message myMessage = new Message(216673380, "xyz302",(String)"simple test");
	publisher.publish(broker, myMessage);
    }
}
