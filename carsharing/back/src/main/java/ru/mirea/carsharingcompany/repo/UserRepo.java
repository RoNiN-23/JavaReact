package ru.mirea.carsharingcompany.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.mirea.carsharingcompany.domain.User;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String name);
    List <User> findAll();
}
