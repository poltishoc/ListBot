package test.spisoktest;

import lombok.NonNull;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import test.spisoktest.config.BotConfig;
import test.spisoktest.entity.Message;
import test.spisoktest.service.MessageService;
import java.util.List;


public class TelegramBot extends TelegramLongPollingBot {

    final String name;
    final String token;
    final MessageService messageService;
    final BotConfig config;
    final String chatId;

    @Override
    public String getBotUsername() {
        return config.getUsername();
    }
    @Override
    public String getBotToken() {
        return config.getToken();
    }

    public TelegramBot(String name, String token, String chatId, DefaultBotOptions options, MessageService messageService, BotConfig config) {
        super(options);
        this.name = name;
        this.token = token;
        this.chatId = chatId;
        this.messageService = messageService;
        this.config = config;
    }


    @Override
    public void onUpdateReceived(@NonNull Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if ("Список".equalsIgnoreCase(messageText)) {
                List<test.spisoktest.entity.Message> messages = messageService.getAllMessages();
                StringBuilder responseText = new StringBuilder("Ваш список:\n");
                for (Message msg : messages) {
                    responseText.append(msg.getContent()).append("\n");
                }
                sendMessage(chatId, responseText.toString());
            } else {
                messageService.saveMessage(messageText);
                sendMessage(chatId, "Я запомнил \uD83E\uDEE1 : " + messageText);
            }
        }
    }


    private void sendMessage(long chatId, String text) {
        SendMessage message  = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
