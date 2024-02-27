package io.nozistance.dsme.service;

import io.nozistance.dsme.entity.Item;
import io.nozistance.dsme.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static java.util.function.Predicate.not;

@Service
@RequiredArgsConstructor
public class ImageCacheInitializationService {

    private final MenuRepository menuRepository;
    private final ImageFetchingService is;

    @Scheduled(cron = "${data.update-frequency}")
    public void initialize() {
        CompletableFuture.allOf(menuRepository.findAll().stream()
                .map(Item::getImage).filter(not(String::isBlank))
                .map(path -> (Runnable) () -> is.fetchFrom(path))
                .map(CompletableFuture::runAsync)
                .toArray(CompletableFuture[]::new)
        ).join();
    }
}
