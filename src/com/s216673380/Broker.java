package com.s216673380;

import java.util.ArrayList;
import java.util.HashMap;

public class Broker {
    ArrayList<Message> mMessagesList; //List of published messages
    HashMap<String, ArrayList<Subscriber>> mTopicSubscribers; //Maps each Topic string to a Subscriber list

    private static Broker sBrokerInstance;

    public static Broker getInstance() {
        if (sBrokerInstance == null) {
            sBrokerInstance = new Broker();
        }
        return sBrokerInstance;
    }

    private Broker(){
        this.mMessagesList = new ArrayList<>();
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
    public void subscribe(Subscriber newSubscriber, String...topics) {
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
    public void unsubscribe(Subscriber subscriberToRemove, String...topics) {
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
     * Forwards messages to all subscribers subscribed to a particular topic
     * @param topic : the particular topic.
     */
    public void publish(String topic) {
        if(isExisting(topic)) {
            for (int i = 0; i < mMessagesList.size(); i++) {
                Message currentMessage = mMessagesList.get(i);
                ArrayList<Subscriber> subscribersList = mTopicSubscribers.get(currentMessage.getTopic());
                for (Subscriber currentSubscriber : subscribersList) {
                    ArrayList<Message> subscriberMessages = currentSubscriber.getMessages();
                    //subscriberMessages.add(currentMessage);
                    //currentSubscriber.addSubcrMessage(subscriberMessages);
                    currentSubscriber.addSubcrMessage(currentMessage);
                }
                mMessagesList.remove(i);
            }
        }
    }

    private boolean isExisting(String...topic){
        return mTopicSubscribers.containsKey(topic);
    }
}
