package io.nozistance.dsme.repository;

import io.nozistance.dsme.model.Item;
import io.nozistance.dsme.util.DayOfWeek;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MenuRepository extends JpaRepository<Item, UUID> {

    @Cacheable("itemsByCategory")
    List<Item> findByCategory(String category);

    @Cacheable("itemsByDayOfWeek")
    List<Item> findByDaysOfWeekContains(DayOfWeek day);

    @Cacheable("itemsByName")
    List<Item> findByNameContaining(String name);
}
