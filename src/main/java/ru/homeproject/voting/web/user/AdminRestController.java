package ru.homeproject.voting.web.user;

import org.springframework.stereotype.Controller;
import ru.homeproject.voting.model.User;
import ru.homeproject.voting.repository.UserRepository;

import java.util.List;

@Controller
public class AdminRestController extends AbstractUserController {
    public AdminRestController(UserRepository repository) {
        super(repository);
    }

    @Override
    public User create(User user) {
        return super.create(user);
    }

    @Override
    public User get(int id) {
        return super.get(id);
    }

    @Override
    public void update(User user, int id) {
        super.update(user, id);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    public User getByMail(String email) {
        return super.getByMail(email);
    }
}
