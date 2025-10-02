package spring.jpa.lesson.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.jpa.lesson.entity.User;
import spring.jpa.lesson.repository.UserRepository;
import spring.jpa.lesson.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Long createUser(User user) {
        var createdUser = userRepository.save(user);

        return createdUser.getId();
    }

    @Override
    public User getUser(Long id) {

        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public Long updateUser(Long id, User user) {
        user.setId(id);
        var updatedUser = userRepository.save(user);

        return updatedUser.getId();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByUsername(String username) {

        return userRepository.getUserByUsername(username).orElseThrow(EntityNotFoundException::new);
    }
}
