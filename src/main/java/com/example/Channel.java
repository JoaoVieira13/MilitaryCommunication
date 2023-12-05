package com.example;

import java.util.ArrayList;
import java.util.List;

public class Channel {
    private int id;

    private String name;

    private List<User> participants = new ArrayList<>();

    private List<Message> messages = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void broadcastMessage(User sender, String messageContent) {
        Message message = new Message(Integer.parseInt(sender.getId()), 0, messageContent);
        messages.add(message);
        participants.forEach(member -> member.receiveMessage(message));
    }

    public void addMember(User user) {
        participants.add(user);
    }

    public void removeMember(User user) {
        participants.remove(user);
    }
}
