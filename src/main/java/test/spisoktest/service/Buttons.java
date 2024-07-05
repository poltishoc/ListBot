package test.spisoktest.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.List;

public class Buttons {
    private static final InlineKeyboardButton SPISOK_BUTTON = new InlineKeyboardButton("Spisok");
    private static final InlineKeyboardButton HELP_BUTTON = new InlineKeyboardButton("Help");

    public static InlineKeyboardMarkup inlineMarkup() {
        SPISOK_BUTTON.setCallbackData("Список");
        HELP_BUTTON.setCallbackData("/help");

        List<InlineKeyboardButton> rowInline = List.of(SPISOK_BUTTON, HELP_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }
}