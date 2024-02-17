package io.nozistance.dsme.repository;

import io.nozistance.dsme.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User, Long> {

}
