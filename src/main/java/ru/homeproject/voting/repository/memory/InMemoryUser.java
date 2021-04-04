package ru.homeproject.voting.repository.memory;

import ru.homeproject.voting.model.User;
import ru.homeproject.voting.repository.UserRepository;

import java.util.List;

public class InMemoryUser implements UserRepository {

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }


}
