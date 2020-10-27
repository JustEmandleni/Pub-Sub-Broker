package com.s216673380;

import java.util.ArrayList;
import java.util.HashMap;

public class Broker {
    //List of published messages
    ArrayList<Message> mMessagesList = new ArrayList<>();
    //Maps each Topic string to a Subscriber list
    HashMap<String, ArrayList<Subscriber>> mTopicSubscribers = new HashMap<>();

    public void addMessage(Message newMessage){
        mMessagesList.add(newMessage);
    }
    public void addSubscriber(Subscriber newSubscriber, String topic) {
        if(isExisting(topic)){
            ArrayList<Subscriber> subscribersList = mTopicSubscribers.get((String)topic);
            if(!subscribersList.contains(newSubscriber)) {
                //Add new subscriber
                subscribersList.add(newSubscriber);
            }
            mTopicSubscribers.put(topic, subscribersList);
        }
        else {
            //Add new topic together with its new subscriber
            ArrayList<Subscriber> subscribers = new ArrayList<>();
            subscribers.add(newSubscriber);
            mTopicSubscribers.put(topic, subscribers);
        }
    }

    public void removeSubscriber(Subscriber subscriberToRemove, String topic) {
        if(isExisting(topic)){
            ArrayList<Subscriber> subscribersList = mTopicSubscribers.get((String)topic);
            if(subscribersList.contains(subscriberToRemove)) {
                subscribersList.remove(subscriberToRemove);
            }
            mTopicSubscribers.put(topic, subscribersList);
        }
    }

    public void sendAllSubscriberMessages() {
        while (!mMessagesList.isEmpty()) {
            for (int i = 0; i < mMessagesList.size(); i++) {
                Message currentMessage = mMessagesList.remove(i);
                ArrayList<Subscriber> subscribersList = mTopicSubscribers.get(currentMessage.getTopic());
                for (Subscriber currentSubscriber : subscribersList) {
                    ArrayList<Message> subscriberMessages = currentSubscriber.getMessages();
                    subscriberMessages.add(currentMessage);
                    currentSubscriber.setAllMessages(subscriberMessages);
                }
            }
        }
    }

    private boolean isExisting(String topic){
        return mTopicSubscribers.containsKey(topic);
    }

}
