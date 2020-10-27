package com.s216673380;

import java.util.ArrayList;

public class Subscriber {
    private ArrayList<Message> mMessages = new ArrayList<>();

    public void register(Broker broker, String...topic){
        broker.addSubscriber(this, topic);
    }

    public void deregister(Broker broker, String...topic){
        broker.removeSubscriber(this, topic);
    }

    public ArrayList<Message> getMessages() {
        return mMessages;
    }

    public void setAllMessages(ArrayList<Message> subscriberMessages) {
        mMessages = subscriberMessages;
    }
}
