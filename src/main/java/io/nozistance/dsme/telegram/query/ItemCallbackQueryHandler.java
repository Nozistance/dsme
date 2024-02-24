package io.nozistance.dsme.telegram.query;

import io.nozistance.dsme.model.Item;
import io.nozistance.dsme.repository.MenuRepository;
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
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ItemCallbackQueryHandler implements CallbackQueryHandler {

    private final MenuRepository menuRepository;
    private final ImageFetchingService imageFetchingService;

    @Override
    @SneakyThrows
    public void answer(Update update, AbsSender sender) {
        UUID itemId = UUID.fromString(args(update));
        Optional<Item> optional = menuRepository.findById(itemId);
        if (optional.isEmpty()) return;
        Item item = optional.get();
        if (item.getImage().isEmpty()) {
            sender.execute(new UpdateAnswer(update, "HELLO BLYAT"));
        } else {
            var photo = getImage(item.getImage());
            sender.execute(SendPhoto.builder()
                    .chatId(update.getCallbackQuery().getMessage().getChatId())
                    .caption(item.getImage())
                    .photo(photo)
                    .build());
        }
    }

    private InputFile getImage(String path) {
        byte[] bytes = imageFetchingService.fetchFrom(path);
        InputStream stream = new ByteArrayInputStream(bytes);
        return new InputFile(stream, path);
    }

    @Override
    public String getQuery() {
        return "item";
    }
}
