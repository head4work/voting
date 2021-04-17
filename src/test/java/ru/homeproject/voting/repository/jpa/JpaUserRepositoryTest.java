package ru.homeproject.voting.repository.jpa;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.homeproject.voting.model.Role;
import ru.homeproject.voting.model.User;
import ru.homeproject.voting.repository.UserRepository;

import java.util.Collections;
import java.util.Date;

@ContextConfiguration({
        "classpath:spring/spring-app.xml"
})
@ExtendWith(SpringExtension.class)
//@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))

class JpaUserRepositoryTest {
    private static final User NEW_USER = new User(null, "name", "name@email.com", "password", true, new Date(), Collections.singleton(Role.USER));

    @Autowired
    private UserRepository repository;

    @org.junit.jupiter.api.Test
    void save() {
        repository.save(NEW_USER);
    }

    @org.junit.jupiter.api.Test
    void get() {
    }

    @org.junit.jupiter.api.Test
    void delete() {
    }

    @org.junit.jupiter.api.Test
    void getByEmail() {
    }

    @org.junit.jupiter.api.Test
    void getAll() {
    }
}