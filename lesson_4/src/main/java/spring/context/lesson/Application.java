package spring.context.lesson;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import spring.context.lesson.database.model.User;
import spring.context.lesson.service.UserService;
import spring.context.lesson.service.impl.UserServiceImpl;

@ComponentScan
public class Application {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

        UserService service = context.getBean(UserServiceImpl.class);

//        var users = service.getAllUsers();
//        System.out.println(users);
//
//        var user = service.getUser(1L);
//        System.out.println(user);

//        var userForCreating = new User();
//        userForCreating.setUsername("ignat");
//        var createdId = service.createUser(userForCreating);
//        System.out.println(createdId);
//        var createdUser = service.getUser(createdId);
//        System.out.println(createdUser);

//        var userForUpdating = new User();
//        userForUpdating.setUsername("fedor");
//        var updatedId = service.updateUser(1L, userForUpdating);
//        var updatedUser = service.getUser(updatedId);
//        System.out.println(updatedUser);

        service.deleteUser(1L);
        var deletedUser = service.getUser(1L);
    }
}
