package io.nozistance.dsme.repository;

import io.nozistance.dsme.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository
        extends JpaRepository<Item, Long> {

    Optional<Item> findByName(String name);
}
