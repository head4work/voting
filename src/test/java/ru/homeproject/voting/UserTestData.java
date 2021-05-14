package ru.homeproject.voting;


import ru.homeproject.voting.model.Role;
import ru.homeproject.voting.model.User;

import java.util.Collections;
import java.util.Date;

import static ru.homeproject.voting.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final ru.homeproject.voting.TestMatcher<User> USER_MATCHER = ru.homeproject.voting.TestMatcher
            .usingIgnoringFieldsComparator(User.class, "registered", "roles", "password");

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int NOT_FOUND = 10;
    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN);
    public static final String jsonUserWithPassword = "{\"name\":\"New\",\"email\":\"new@gmail.com\",\"password\":\"newPass\",\"enabled\":false,\"" +
            "registered\":\"2021-05-14T10:10:38.995+00:00\",\"roles\":[\"USER\"]}";

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(USER);
        updated.setEmail("update@gmail.com");
        updated.setName("UpdatedName");
        updated.setPassword("newPass");
        updated.setEnabled(false);
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }
}
