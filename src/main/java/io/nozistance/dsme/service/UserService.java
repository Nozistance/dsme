package io.nozistance.dsme.service;

import io.nozistance.dsme.model.User;
import io.nozistance.dsme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void subscribe(Update update) {
        long id = update.getMessage().getChatId();
        User user = userRepository.findById(id)
                .orElseGet(() -> create(update));
        user.setSubscribed(true);
    }

    @Transactional
    public void unsubscribe(Update update) {
        long id = update.getMessage().getChatId();
        User user = userRepository.findById(id)
                .orElseGet(() -> create(update));
        user.setSubscribed(false);
    }

    public boolean isSubscribed(Update update) {
        long id = update.getMessage().getChatId();
        return userRepository.findById(id)
                .map(User::getSubscribed)
                .orElse(false);
    }

    public User getOrCreate(Update update) {
        Long chatId = update.getMessage().getChatId();
        return userRepository.findById(chatId).orElseGet(() -> {
            User newUser = create(update);
            userRepository.save(newUser);
            return newUser;
        });
    }

    private User create(Update update) {
        var info = update.getMessage().getChat();
        return User.builder()
                .chatId(update.getMessage().getChatId())
                .firstName(info.getFirstName())
                .lastName(info.getLastName())
                .username(info.getUserName())
                .subscribed(true)
                .build();
    }
}
