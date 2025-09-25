package spring.context.lesson.database.dao;

import spring.context.lesson.database.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Long createUser(User user);

    Optional<User> getUser(Long id);

    List<User> getAllUsers();

    Long updateUser(Long id, User user);

    void deleteUser(Long id);
}
