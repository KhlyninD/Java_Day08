package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

public class Main {

    public static void main(String[] argc) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        UsersRepositoryJdbcImpl usersRepositoryJdbcImpl = context.getBean("usersRepositoryJdbcImpl", UsersRepositoryJdbcImpl.class);
        usersRepositoryJdbcImpl.save(new User(1L, "111@ya.ru"));
        usersRepositoryJdbcImpl.save(new User(2L, "222@ya.ru"));
        usersRepositoryJdbcImpl.save(new User(3L, "333@ya.ru"));
        usersRepositoryJdbcImpl.save(new User(4L, "444@ya.ru"));
        System.out.println(usersRepositoryJdbcImpl.findAll());
        usersRepositoryJdbcImpl.delete(1L);
        System.out.println(usersRepositoryJdbcImpl.findById(1L));
        System.out.println(usersRepositoryJdbcImpl.findAll());
        usersRepositoryJdbcImpl.update(new User(2L, "tttt@test"));
        System.out.println(usersRepositoryJdbcImpl.findAll());
        System.out.println(usersRepositoryJdbcImpl.findById(2L));
        System.out.println(usersRepositoryJdbcImpl.findByEmail("444@ya.ru").get());

        System.out.println("-----------------------------------------------------");

        UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate = context.getBean("usersRepositoryJdbcTemplate", UsersRepositoryJdbcTemplateImpl.class);
        usersRepositoryJdbcTemplate.save(new User(1L, "111@ya.ru"));
        usersRepositoryJdbcTemplate.save(new User(2L, "222@ya.ru"));
        usersRepositoryJdbcTemplate.save(new User(3L, "333@ya.ru"));
        usersRepositoryJdbcTemplate.save(new User(4L, "444@ya.ru"));
        System.out.println(usersRepositoryJdbcTemplate.findAll());
        usersRepositoryJdbcTemplate.delete(1L);
        System.out.println(usersRepositoryJdbcTemplate.findAll());
        System.out.println(usersRepositoryJdbcTemplate.findById(1L));
        usersRepositoryJdbcTemplate.update(new User(2L, "tttt@test"));
        System.out.println(usersRepositoryJdbcTemplate.findAll());
        System.out.println(usersRepositoryJdbcTemplate.findById(2L));
        System.out.println(usersRepositoryJdbcTemplate.findByEmail("444@ya.ru").get());
        context.close();
    }
}
