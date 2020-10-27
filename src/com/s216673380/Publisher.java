package com.s216673380;

public class Publisher {
    /**
     * Sends message to Broker.
     * @param broker : a "middleman" or "post office" in lame man's terms
     * @param message : defines the publishing sender, topic and content
     */
    public void sendMessage(Broker broker, Message message){
        broker.addMessage(message);
    }
}
