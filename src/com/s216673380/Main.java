package com.s216673380;

public class Main {

    public static void main(String[] args) {
	Subscriber subscriber = new Subscriber();
	Broker broker = Broker.getInstance();
	subscriber.register(broker, "wrpv302", "wrr301", "xyz302", "wrev301", "wrdb301", "wuiv301");
	subscriber.deregister(broker, "xyz302");
    }
}
