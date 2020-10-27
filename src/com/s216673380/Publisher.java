package com.s216673380;

public class Publisher {
    public void publish (Broker broker, Message message){
        broker.addMessage(message);
    }
}
