package spring.jpa.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.jpa.lesson.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserByUsername(String username);
}
