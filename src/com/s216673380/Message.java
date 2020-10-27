package com.s216673380;

public class Message {
    private String mTopic;
    private Object mContent;
    private int mPublisherId;

    public Message(int publisherId, String topic, Object content){
        mPublisherId = publisherId;
        mTopic = topic;
        mContent = content;
    }

    public boolean hasSame(String topic){
        return mTopic.equalsIgnoreCase(topic);
    }

    public String getTopic() {
        return mTopic;
    }

    public void setTopic(String topic) {
        mTopic = topic;
    }

    public Object getContent() {
        return mContent;
    }

    public void setContent(Object content) {
        mContent = content;
    }

    public int getPublisherId() {
        return mPublisherId;
    }

    public void setPublisherId(int publisherId) {
        mPublisherId = publisherId;
    }
}