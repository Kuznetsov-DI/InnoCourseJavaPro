package spring.jpa.lesson.runner;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import spring.jpa.lesson.entity.User;
import spring.jpa.lesson.service.impl.UserServiceImpl;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final UserServiceImpl userService;

    @Override
    @Transactional
    public void run(String... args) {
        var user = userService.getUser(1L);
        log.info("Got user - {}", user);

        var userForCreating = new User();
        userForCreating.setUsername("New User");
        var createdUserId = userService.createUser(userForCreating);
        log.info("User created with id - {}", createdUserId);
    }
}
