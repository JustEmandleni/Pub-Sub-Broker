package com.s216673380;

import java.util.ArrayList;

public class Subscriber {
    private ArrayList<Message> mMessages = new ArrayList<>();

    public void register(Broker broker, String...topic){
        broker.subscribe(this, topic);
    }

    public void deregister(Broker broker, String...topic){
        broker.unsubscribe(this, topic);
    }

    public ArrayList<Message> getMessages() {
        return mMessages;
    }

    public void addSubcrMessage(Message newMessage) {
        mMessages.add(newMessage);
    }
}
