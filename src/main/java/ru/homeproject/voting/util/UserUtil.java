package ru.homeproject.voting.util;

import ru.homeproject.voting.model.User;
import ru.homeproject.voting.to.UserTo;

public class UserUtil {

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }
}
