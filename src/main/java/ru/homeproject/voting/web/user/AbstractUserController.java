package ru.homeproject.voting.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ru.homeproject.voting.model.User;
import ru.homeproject.voting.repository.UserRepository;
import ru.homeproject.voting.web.AuthorizedUser;

import java.util.List;

import static ru.homeproject.voting.util.ValidationUtil.*;

@Component("users")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AbstractUserController implements UserDetailsService {

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

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
