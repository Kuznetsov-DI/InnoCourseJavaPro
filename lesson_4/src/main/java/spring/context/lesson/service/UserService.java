package spring.context.lesson.service;

import spring.context.lesson.database.model.User;

import java.util.List;

public interface UserService {
    Long createUser(User user);

    User getUser(Long id);

    List<User> getAllUsers();

    Long updateUser(Long id, User user);

    void deleteUser(Long id);
}
