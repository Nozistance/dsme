package io.nozistance.dsme.telegram.query;

import io.nozistance.dsme.entity.Item;
import io.nozistance.dsme.repository.MenuRepository;
import io.nozistance.dsme.service.AnswerTextService;
import io.nozistance.dsme.service.ImageFetchingService;
import io.nozistance.dsme.telegram.UpdateAnswer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;

import static java.util.stream.Collectors.joining;

@Component
@RequiredArgsConstructor
public class ItemCallbackQueryHandler implements CallbackQueryHandler {

    private final MenuRepository menuRepository;
    private final ImageFetchingService imageFetchingService;
    private final AnswerTextService answers;

    @Override
    @SneakyThrows
    public void answer(Update update, AbsSender sender, String args) {
        UUID itemId = UUID.fromString(args);
        var optional = menuRepository.findById(itemId);
        if (optional.isEmpty()) return;
        Item item = optional.get();
        String text = getAnswerText(item);
        if (!item.getImage().isEmpty()) {
            var photo = getImage(item.getImage());
            sender.execute(SendPhoto.builder()
                    .chatId(update.getCallbackQuery()
                            .getMessage().getChatId())
                    .parseMode("Markdown")
                    .caption(text)
                    .photo(photo)
                    .build());
        } else {
            sender.execute(new UpdateAnswer(update, text));
        }
    }

    private InputFile getImage(String path) {
        byte[] bytes = imageFetchingService.fetchFrom(path);
        InputStream stream = new ByteArrayInputStream(bytes);
        return new InputFile(stream, path);
    }

    private String getAnswerText(Item item) {
        return answers.getAnswer("item-info",
                item.getName(),
                item.getCategory(),
                item.getCalories(),
                item.getPrice(),
                item.getWeight(),
                item.getIngredients(),
                item.getDaysOfWeek().stream()
                        .sorted(Enum::compareTo)
                        .map(Object::toString)
                        .collect(joining(", ")));
    }

    @Override
    public String getQuery() {
        return "item";
    }
}
