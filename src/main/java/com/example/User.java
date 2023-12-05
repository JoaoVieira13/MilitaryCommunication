package com.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    
    private String name;

    private String password;

    private String profile;

    private List<Message> inbox = new ArrayList<>();

    private List<Channel> channels = new ArrayList<>();

    public User() {
        this.id = generateUniqueId();
    }

    private String generateUniqueId() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        return now.format(formatter);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public List<Message> getInbox() {
        return inbox;
    }

    public void setInbox(List<Message> inbox) {
        this.inbox = inbox;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public void sendMessage(User recipient, String messageContent) {
        Message message = new Message(Integer.parseInt(this.getId()), Integer.parseInt(recipient.getId()), messageContent);
        recipient.receiveMessage(message);
    }

    public void receiveMessage(Message message) {
        inbox.add(message);
    }

    public void broadcastMessageToChannel(Channel channel, String messageContent) {
        channel.broadcastMessage(this, messageContent);
    }

    public void joinChannel(Channel channel) {
        channels.add(channel);
        channel.addMember(this);
    }

    public void leaveChannel(Channel channel) {
        channels.remove(channel);
        channel.removeMember(this);
    }
}
