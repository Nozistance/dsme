package io.nozistance.dsme.repository;

import io.nozistance.dsme.entity.Item;
import io.nozistance.dsme.util.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MenuRepository
        extends JpaRepository<Item, UUID> {

    List<Item> findByDaysOfWeekContains(DayOfWeek day);
}
