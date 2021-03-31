package ru.homeproject.voting.repository.memory;

import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.model.User;
import ru.homeproject.voting.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUser implements UserRepository {

    private final static Map<Integer, Map<LocalDate, Boolean>> votes = new ConcurrentHashMap<>();
    private final static Map<LocalDate, Boolean> dateVotes = new ConcurrentHashMap<>();
    private final static Map<LocalDate, Boolean> dateVotes1 = new ConcurrentHashMap<>();
    private final static Map<LocalDate, Boolean> dateVotes2 = new ConcurrentHashMap<>();

    static {
        dateVotes.put(LocalDate.now(), true);
        dateVotes1.put(LocalDate.now(), true);
        dateVotes2.put(LocalDate.now(), true);
        votes.put(1, dateVotes);
        votes.put(2, dateVotes1);
        votes.put(3, dateVotes2);
    }

    public boolean hasVote(int userId, LocalDate date) {
        Map<LocalDate, Boolean> dateBooleanMap = votes.get(userId);
        return dateBooleanMap.get(date);
    }

    public void banVote(int userId, LocalDate date) {
        Map<LocalDate, Boolean> dateMap = votes.get(userId);
        dateMap.replace(date,true,false);
    }

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
