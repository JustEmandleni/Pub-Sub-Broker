package com.s216673380;

public class Message {
    private String mTopic;
    private String mContent;
    private int mPublisherId;

    public Message(int publisherId, String topic, String content){
        mPublisherId = publisherId;
        mTopic = topic;
        mContent = content;
    }

    public String getTopic() {
        return mTopic;
    }

    public void setTopic(String topic) {
        mTopic = topic;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public int getPublisherId() {
        return mPublisherId;
    }

    public void setPublisherId(int publisherId) {
        mPublisherId = publisherId;
    }
}