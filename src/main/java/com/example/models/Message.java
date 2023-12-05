package com.example.models;

public class Message {
    private int idFrom;

    private int idTo;

    private String content;
    
    private boolean viewed;

    public int getIdFrom() {
        return idFrom;
    }

    public void setIdFrom(int idFrom) {
        this.idFrom = idFrom;
    }

    public int getIdTo() {
        return idTo;
    }

    public void setIdTo(int idTo) {
        this.idTo = idTo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }
    
    public Message(int idFrom, int idTo, String content) {
        this.idFrom = idFrom;
        this.idTo = idTo;
        this.content = content;
        this.viewed = false;
    }
}
