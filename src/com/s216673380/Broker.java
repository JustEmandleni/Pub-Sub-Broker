package com.s216673380;

import java.util.ArrayList;
import java.util.HashMap;

public class Broker {
    ArrayList<Message> mMessagesList = new ArrayList<>();
    //Mapping Topics to individual lists of Subscribers
    HashMap<String, ArrayList<Subscriber>> topicSubscribers = new HashMap<>();

    public void addMessages(Message message){
        mMessagesList.add(message);
    }

    public void getSubscriberMessages(Subscriber subscriber, String topic) { //getMessforSubscrTopi
        if(topicSubscribers.containsKey(topic)) {
            while (!mMessagesList.isEmpty()) {
                for (int i = 0; i < mMessagesList.size(); i++) {
                    Message currentMessage = mMessagesList.get(i);
                    if (currentMessage.hasSame(topic)) {
                        ArrayList subscribersList = topicSubscribers.get(topic);
                        
                    }
                }
            }
        }
    }

    public void addSubscriber(Subscriber subcrToAdd, String topic) {
        if(topicSubscribers.containsKey(topic)){
            //Add new subscriber to existing topic
            ArrayList<Subscriber> subscribersList = topicSubscribers.get((String)topic);
            if(!subscribersList.contains(subcrToAdd))
                subscribersList.add(subcrToAdd);
            topicSubscribers.put(topic, subscribersList);
        }
        else {
            //Add new topic together with its new subscriber
            ArrayList<Subscriber> subscribers = new ArrayList<>();
            subscribers.add(subcrToAdd);
            topicSubscribers.put(topic, subscribers);
        }
    }

    public void removeSubscriber(Subscriber subscrToRemove, String topic) {
        if(topicSubscribers.containsKey(topic)){
            ArrayList<Subscriber> subscribersList = topicSubscribers.get((String)topic);
            if(subscribersList.contains(subscrToRemove))
                subscribersList.remove(subscrToRemove);
            topicSubscribers.put(topic, subscribersList);
        }
    }
}
