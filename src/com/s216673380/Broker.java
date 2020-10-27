package com.s216673380;

import java.util.ArrayList;
import java.util.HashMap;

public class Broker {
    ArrayList<Message> mMessagesList = new ArrayList<>(); //List of published messages
    HashMap<String, ArrayList<Subscriber>> mTopicSubscribers; //Maps each Topic string to a Subscriber list

    private static Broker sBrokerInstance;

    public static Broker getInstance() {
        if (sBrokerInstance == null) {
            sBrokerInstance = new Broker();
        }
        return sBrokerInstance;
    }

    private Broker(){
        this.mTopicSubscribers = new HashMap<>();
    }

    public void addMessage(Message newMessage){
        mMessagesList.add(newMessage);
    }

    /**
     * Allow Subscriber to register an indeterminate number of parameters
     * @param newSubscriber : the calling Subscriber
     * @param topics : one or more topics
     */
    public void addSubscriber(Subscriber newSubscriber, String...topics) {
        for(String currentTopic : topics) {
            if(isExisting(currentTopic)) {
                ArrayList<Subscriber> subscribersList = mTopicSubscribers.get(currentTopic);
                if (!subscribersList.contains(newSubscriber)) {
                    //Add new subscriber
                    subscribersList.add(newSubscriber);
                }
                mTopicSubscribers.put(currentTopic, subscribersList);
            }
            else {
                //Add new topic together with its new subscriber
                ArrayList<Subscriber> subscribers = new ArrayList<>();
                subscribers.add(newSubscriber);
                mTopicSubscribers.put(currentTopic, subscribers);
            }
        }
    }

    /**
     * Allow the calling Subscriber to deregister an indeterminate number of parameters
     * @param subscriberToRemove : the calling subscriber
     * @param topics : one or more topics
     */
    public void removeSubscriber(Subscriber subscriberToRemove, String...topics) {
        for(String currentTopic : topics) {
            if (isExisting(currentTopic)) {
                ArrayList<Subscriber> subscribersList = mTopicSubscribers.get(currentTopic);
                if (subscribersList.contains(subscriberToRemove)) {
                    subscribersList.remove(subscriberToRemove);
                }
                mTopicSubscribers.put(currentTopic, subscribersList);
            }
        }
    }

    /**
     * Forwards messages to all subscribers that registered an interest in particularly specified topic(s)
     */
    public void fowardMessagesToAllSubscribers() {
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

    private boolean isExisting(String...topic){
        return mTopicSubscribers.containsKey(topic);
    }
}
