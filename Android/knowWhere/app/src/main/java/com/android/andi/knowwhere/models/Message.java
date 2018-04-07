package com.android.andi.knowwhere.models;

/**
 * Created by Andi Xu on 4/7/18.
 */

public class Message {
    private String sender;
    private String groupname;
    private String content;

    public Message(String sender, String groupname, String content) {
        this.sender = sender;
        this.groupname = groupname;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
