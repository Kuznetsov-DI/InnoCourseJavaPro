package spring.jpa.lesson.service;

import spring.jpa.lesson.entity.User;

import java.util.List;

public interface UserService {

    Long createUser(User user);

    User getUser(Long id);

    List<User> getAllUsers();

    Long updateUser(Long id, User user);

    void deleteUser(Long id);

    User getUserByUsername(String username);
}
