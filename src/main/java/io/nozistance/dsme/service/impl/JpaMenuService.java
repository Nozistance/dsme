package io.nozistance.dsme.service.impl;

import io.nozistance.dsme.model.Item;
import io.nozistance.dsme.repository.MenuRepository;
import io.nozistance.dsme.service.MenuService;
import io.nozistance.dsme.util.DayOfWeek;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JpaMenuService implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    public List<Item> searchByName(String name) {
        return menuRepository.findByNameContaining(name);
    }

    @Override
    public List<Item> searchByDay(DayOfWeek day) {
        return menuRepository.findByDaysOfWeekContains(day);
    }
}
