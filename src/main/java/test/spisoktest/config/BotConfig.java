package test.spisoktest.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Data
@Configuration
@PropertySource("classpath:application.properties")
public class BotConfig {

    @Value("${bot.name}") private String username;

    @Value("${bot.token}") private String token;

    @Value("${bot.chatId}") private String chatId;
}
