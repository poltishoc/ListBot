package test.spisoktest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.spisoktest.entity.Message;
import test.spisoktest.service.MessageService;

import java.util.List;

@RestController
public class BotController  {

    @Autowired
    private MessageService messageService;

    @GetMapping("/api/messages")
    public List<Message> getMessages(@RequestParam long userId) {
        return messageService.getAllMessagesByUserId(userId);
    }

    @GetMapping("/api/respond")
    public Message respond(@RequestParam String message, @RequestParam long userId) {
        return messageService.saveMessage(message, userId);
    }
}
