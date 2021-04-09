package ru.homeproject.voting.web.user;

import org.springframework.stereotype.Controller;
import ru.homeproject.voting.model.User;
import ru.homeproject.voting.repository.UserRepository;
import ru.homeproject.voting.web.SecurityUtil;

@Controller
public class ProfileRestController extends AbstractUserController {
    public ProfileRestController(UserRepository repository) {
        super(repository);
    }

    public User get() {
        return super.get(SecurityUtil.authUserId());
    }

    public void update(User user) {
        super.update(user, SecurityUtil.authUserId());
    }

    public void delete() {
        super.delete(SecurityUtil.authUserId());
    }
}
