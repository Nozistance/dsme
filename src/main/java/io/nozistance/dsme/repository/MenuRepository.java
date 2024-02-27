package io.nozistance.dsme.repository;

import io.nozistance.dsme.entity.Item;
import io.nozistance.dsme.util.DayOfWeek;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MenuRepository extends JpaRepository<Item, UUID> {

    @Cacheable("itemsByCategory")
    List<Item> findByCategory(String category);

    @Cacheable("itemsByDayOfWeek")
    List<Item> findByDaysOfWeekContains(DayOfWeek day);

    @Cacheable("itemsByName")
    @Query("SELECT e FROM Item e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Item> findByName(@Param("name") String name);
}
