package ru.homeproject.voting.repository.memory;

import ru.homeproject.voting.model.User;
import ru.homeproject.voting.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryUser implements UserRepository {
    private final AtomicInteger counter = new AtomicInteger();
    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            users.put(user.getId(), user);
            return user;
        }
        return users.computeIfAbsent(user.getId(), integer -> user);
    }

    @Override
    public boolean delete(int id) {
        return users.remove(id) != null;
    }

    @Override
    public User get(int id) {
        return users.get(id);
    }

    @Override
    public User getByEmail(String email) {
        return users.values().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    @Override
    public List<User> getAll() {
        return users.values().stream().sorted(Comparator.comparing(User::getName).thenComparing(User::getEmail)).collect(Collectors.toList());
    }


}
