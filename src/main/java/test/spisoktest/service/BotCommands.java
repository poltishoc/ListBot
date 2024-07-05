package test.spisoktest.service;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public interface BotCommands {
    List<BotCommand> LIST_OF_COMMANDS = List.of(
            new BotCommand("Список", "Вернуть список") ,
            new BotCommand("/help", "bot info")
    );
    String HELP_TEXT = "Я запомню все твои необходимые покупки, " +
            "а когда тебе надо будет их вспомнить напиши мне <Список>";
}