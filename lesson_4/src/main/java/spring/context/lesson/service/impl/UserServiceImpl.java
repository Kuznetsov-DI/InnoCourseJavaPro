package spring.context.lesson.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.context.lesson.database.dao.UserDao;
import spring.context.lesson.database.model.User;
import spring.context.lesson.service.UserService;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public Long createUser(User user) {
        return userDao.createUser(user);
    }

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id)
                .orElseThrow(() -> new RuntimeException("User didn't find by id - " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public Long updateUser(Long id, User user) {
        return userDao.updateUser(id, user);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
}
