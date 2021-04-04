package ru.homeproject.voting.repository.memory;

import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.model.User;
import ru.homeproject.voting.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUser implements UserRepository {

    private final static Map<Integer, Map<LocalDate, Integer>> votes = new ConcurrentHashMap<>();
    private final static Map<LocalDate, Integer> dateVotes = new ConcurrentHashMap<>();
    private final static Map<LocalDate, Integer> dateVotes1 = new ConcurrentHashMap<>();
    private final static Map<LocalDate, Integer> dateVotes2 = new ConcurrentHashMap<>();

    static {
        dateVotes.put(LocalDate.now(), 0);
        dateVotes1.put(LocalDate.now(), 0);
        dateVotes2.put(LocalDate.now(), 0);
        votes.put(1, dateVotes);
        votes.put(2, dateVotes1);
        votes.put(3, dateVotes2);
    }

    public boolean hasVote(int userId, LocalDate date) {
        Map<LocalDate, Integer> dateBooleanMap = votes.get(userId);
        return dateBooleanMap.get(date)==0;
    }

    public void banVote(Restaurant r, int userId, LocalDate date) {
        Map<LocalDate, Integer> dateMap = votes.get(userId);
        dateMap.replace(date,0,r.getId());
    }
    public Integer getRevokedRestaurantId(int userId, LocalDate now) {
        Map<LocalDate, Integer> dateMap = votes.get(userId);
        return dateMap.get(now);
    }
    public void resetVote(int userId, LocalDate date) {
        Map<LocalDate, Integer> dateMap = votes.get(userId);

        dateMap.computeIfPresent(date, (date1, integer) -> 0);
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
