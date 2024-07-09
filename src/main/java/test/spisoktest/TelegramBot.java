package test.spisoktest;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import test.spisoktest.config.BotConfig;
import test.spisoktest.entity.Message;
import test.spisoktest.service.MessageService;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    final BotConfig config;
    final MessageService messageService;

    public TelegramBot(MessageService messageService, BotConfig config) {
        this.config = config;
        this.messageService = messageService;
 }
    @Override
    public String getBotUsername() {
        return config.getUsername();
    }
    @Override
    public String getBotToken(){
        return config.getToken();
    }


    @Override
    public void onUpdateReceived(@NonNull Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            long userId = update.getMessage().getFrom().getId();

            Message(messageText, chatId, userId, update.getMessage().getChat().getFirstName());
        }
    }

    private void Message(String messageText, long chatId, long userId, String firstName) {
        switch (messageText.toLowerCase()) {
            case "/start":
                handleStartCommand(chatId, userId, firstName);
                break;
            case "список":
                handleListCommand(chatId, userId);
                break;
            case "очистка":
                handleClearCommand(chatId, userId);
                break;
            default:
                handleSaveCommand(messageText, chatId, userId);
                break;
        }
    }

    private void handleStartCommand(long chatId, long userId, String firstName) {
        String answer = "\uD83D\uDC4B Привет, " + firstName + ", я вместо тебя запомню все необходимые покупки."
                + "\n" + "Просто напишимне их мне и запомню, а когда понадобится список - так и напише мне 'спиок' или выбери комнду"
                + "\n" + "Когда захочешь очистить список, напиши 'очистка' или выбери  такую комнду \uD83E\uDD1D";
        sendMessage(chatId, answer);
    }

    private void handleListCommand(long chatId, long userId) {
        List<Message> messages = messageService.getAllMessagesByUserId(userId);
        StringBuilder responseText  = new StringBuilder("\uD83C\uDF65 Ваш список: \n");
        for (Message msg     : messages) {
            responseText.append(msg.getContent()).append("\n");
        }
        sendMessage(chatId, responseText.toString());
    }

    private void handleClearCommand (long chatId, long userId) {
        messageService.deleteMessageById(userId);
        sendMessage(chatId, "\uD83E\uDD24 Я очистил список.");
    }

    private void handleSaveCommand(String messageText, long chatId, long userId) {
        messageService.saveMessage(messageText, userId);
        sendMessage(chatId, "\uD83E\uDEE1 Я запомнил: " + messageText);
    }


    private void sendMessage(long chatId, String text) {
        SendMessage message  = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row  = new KeyboardRow();
        row.add("Список");
        row.add("Очистка");
        keyboardRows.add(row);

        keyboardMarkup.setKeyboard(keyboardRows);
        message.setReplyMarkup(keyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
