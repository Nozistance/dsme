package io.nozistance.dsme.repository;

import io.nozistance.dsme.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User, Long> {

}
