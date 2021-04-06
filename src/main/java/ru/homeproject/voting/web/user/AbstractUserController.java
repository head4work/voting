package ru.homeproject.voting.web.user;

import ru.homeproject.voting.model.User;
import ru.homeproject.voting.repository.UserRepository;

import java.util.List;

import static ru.homeproject.voting.util.ValidationUtil.*;

public class AbstractUserController {
    private final UserRepository repository;

    public AbstractUserController(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        checkNew(user);
        // assert User not null
        return repository.save(user);
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public void update(User user, int id) {
        assureIdConsistent(user, id);
        // assert user not null
        checkNotFoundWithId(repository.save(user), user.id());
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    public User getByMail(String email) {
        // assert email not null
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

}
