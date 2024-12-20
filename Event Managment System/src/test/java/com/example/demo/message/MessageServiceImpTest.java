package com.example.demo.message;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.util.List;
import java.util.Optional; 

class MessageServiceImpTest {
  
    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageServiceImp messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveMessage_Success() {
        Message message = new Message();
        Mockito.when(messageRepository.save(Mockito.any(Message.class))).thenReturn(message);

        Message savedMessage = messageService.saveMessage(message);

        assertThat(savedMessage).isNotNull();
        Mockito.verify(messageRepository, Mockito.times(1)).save(message);
    }

    @Test
    void testGetAllMessages() {
        List<Message> messages = List.of(new Message(), new Message());
        Mockito.when(messageRepository.findAll()).thenReturn(messages);

        List<Message> fetchedMessages = messageService.getAllMessages();

        assertThat(fetchedMessages).hasSize(2);
        Mockito.verify(messageRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testGetMessageById_Success() {
        int messageId = 1;
        Message message = new Message();
        Mockito.when(messageRepository.findById(messageId)).thenReturn(Optional.of(message));

        Message fetchedMessage = messageService.getMessageById(messageId);

        assertThat(fetchedMessage).isNotNull();
        Mockito.verify(messageRepository, Mockito.times(1)).findById(messageId);
    }

    @Test
    void testGetMessageById_NotFound() {
        int messageId = 1;
        Mockito.when(messageRepository.findById(messageId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> messageService.getMessageById(messageId))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Message not found");
    }

    @Test
    void testDeleteMessage() {
        int messageId = 1;
        Mockito.doNothing().when(messageRepository).deleteById(messageId);

        messageService.deleteMessage(messageId);

        Mockito.verify(messageRepository, Mockito.times(1)).deleteById(messageId);
    }
}
