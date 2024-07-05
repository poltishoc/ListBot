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

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message saveMessage(String content) {
        Message message = new Message(content);
        return messageRepository.save(message);
    }
}
