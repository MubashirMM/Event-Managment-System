package com.example.demo.message;

import java.util.List;

public interface MessageService {
    Message saveMessage(Message message);
    List<Message> getAllMessages();
    Message getMessageById(int id);
    void deleteMessage(int id);
}
