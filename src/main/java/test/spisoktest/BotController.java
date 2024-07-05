package test.spisoktest;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import test.spisoktest.entity.Message;
import test.spisoktest.service.MessageService;

import java.util.List;

@RestController
public class BotController  {

    @Autowired
    private MessageService messageService;

    @GetMapping("/api/messages")
    public List<Message> getMessages() {
        return messageService.getAllMessages();
    }

    public Message respond(@RequestParam(value = "message") String message) {
        return messageService.saveMessage(message);
    }

}
