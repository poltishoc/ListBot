package test.spisoktest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.spisoktest.entity.Message;
import test.spisoktest.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAllMessagesByUserId(long userId) {
        return messageRepository.findByUserId(userId);
    }

    public Message saveMessage(String content, long userId) {
        Message message = new Message(content, userId);
        return messageRepository.save(message);
    }

    public void deleteMessageById(long userId) {
        List<Message> messages = messageRepository.findByUserId(userId);
        messageRepository.deleteAll(messages);

    }
}

