package test.spisoktest.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import test.spisoktest.TelegramBot;
import test.spisoktest.service.MessageService;

@Data
@Configuration
@PropertySource("classpath:config.properties")
public class BotConfig {

    @Value("${bot.name}")
    private String username;

    @Value("${bot.token}")
    private String token;

    @Value("${bot.chatId}")
    private String chatId;

    @Bean
    public TelegramBotsApi telegramBotsApi(MessageService messageService) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(new TelegramBot(username, token, chatId, new DefaultBotOptions(), messageService, new BotConfig()));
        return botsApi;
    }
}