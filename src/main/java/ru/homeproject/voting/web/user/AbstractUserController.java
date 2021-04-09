package ru.homeproject.voting.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import ru.homeproject.voting.model.User;
import ru.homeproject.voting.repository.UserRepository;

import java.util.List;

import static ru.homeproject.voting.util.ValidationUtil.*;

public class AbstractUserController {

    @Autowired
    private final UserRepository repository;

    public AbstractUserController(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        checkNew(user);
        Assert.notNull(user, "User must not be null");
        return repository.save(user);
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public void update(User user, int id) {
        assureIdConsistent(user, id);
        Assert.notNull(user, "User must not be null");
        checkNotFoundWithId(repository.save(user), user.id());
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    public User getByMail(String email) {
        Assert.notNull(email, "Email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

}
