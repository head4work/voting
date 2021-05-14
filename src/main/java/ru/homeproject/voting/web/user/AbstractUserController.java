package ru.homeproject.voting.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ru.homeproject.voting.model.User;
import ru.homeproject.voting.repository.UserRepository;
import ru.homeproject.voting.web.AuthorizedUser;

import java.util.List;

import static ru.homeproject.voting.util.UserUtil.prepareToSave;
import static ru.homeproject.voting.util.ValidationUtil.*;

@Component("users")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AbstractUserController implements UserDetailsService {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private final UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AbstractUserController(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        Assert.notNull(user, "User must not be null");
        return repository.save(prepareToSave(user, passwordEncoder));
    }

    public User get(int id) {
        log.info("get {}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    public void update(User user, int id) {
        log.info("update {}", id);
        assureIdConsistent(user, id);
        Assert.notNull(user, "User must not be null");
        checkNotFoundWithId(repository.save(prepareToSave(user, passwordEncoder)), user.id());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    public List<User> getAll() {
        log.info("getAll");
        return repository.getAll();
    }

    public User getByMail(String email) {
        log.info("get {}", email);
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
