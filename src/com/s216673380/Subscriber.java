package com.s216673380;

import java.util.ArrayList;

public class Subscriber {
    private ArrayList<Message> mMessages = new ArrayList<>();

    public void register(String topic, Broker broker){
        broker.addSubscriber(this, topic);
    }

    public void deregister(String topic, Broker broker){
        broker.removeSubscriber(this, topic);
    }

    public ArrayList<Message> getMessages() {
        return mMessages;
    }

    public void setAllMessages(ArrayList<Message> subscriberMessages) {
        mMessages = subscriberMessages;
    }
}
