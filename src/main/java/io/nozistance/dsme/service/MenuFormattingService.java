package io.nozistance.dsme.service;

import io.nozistance.dsme.entity.Item;
import io.nozistance.dsme.repository.MenuRepository;
import io.nozistance.dsme.util.DayOfWeek;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@Cacheable
@AllArgsConstructor
public class MenuFormattingService {

    private final MenuRepository menuRepository;

    public List<List<Pair<UUID, String>>> getMenu() {
        return Arrays.stream(DayOfWeek.values())
                .map(this::getMenu)
                .toList();
    }

    public List<Pair<UUID, String>> getMenu(DayOfWeek day) {
        return menuRepository.findByDaysOfWeekContains(day)
                .stream().sorted(Comparator.comparing(Item::getName))
                .sorted(Comparator.comparing(Item::getCategory))
                .map(i -> {
                    String name = i.getName();
                    String price = i.getPrice();
                    return Pair.of(i.getId(),
                            "%s | %s".formatted(name, price));
                }).toList();
    }
}
