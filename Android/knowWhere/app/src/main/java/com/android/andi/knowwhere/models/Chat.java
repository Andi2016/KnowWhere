package com.android.andi.knowwhere.models;

/**
 * Created by Andi Xu on 4/6/18.
 */

public class Chat {
    private String sender;
    private String groupname;
    private String timeStamp;
    private String content;

    public Chat(String sender, String groupname, String timeStamp, String content) {
        this.sender = sender;
        this.groupname = groupname;
        this.timeStamp = timeStamp;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
