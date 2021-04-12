package ru.homeproject.voting.repository.memory;

import org.springframework.stereotype.Repository;
import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class InMemoryVote implements VoteRepository {
    // date , user_id , restaurant_id
    private final static Map<LocalDate, Map<Integer, Integer>> votes = new ConcurrentHashMap<>();

    @Override
    public void saveVote(Restaurant r, int userId) {
        if (userHasVote(userId)) {
            votes.computeIfAbsent(LocalDate.now(), date -> new ConcurrentHashMap<>());
            votes.get(LocalDate.now()).put(userId, r.id());
        } else if (LocalDateTime.now().getHour() < 11) {
            votes.get(LocalDate.now()).computeIfPresent(userId, (userId1, restId) -> r.id());
        } else {
            System.out.println("U'v already voted and it's too late to change vote");
        }
    }

    private boolean userHasVote(int userId) {
        if (votes.get(LocalDate.now()) != null) {
            return !votes.get(LocalDate.now()).containsKey(userId);
        }
        return true;
    }

    @Override
    public Integer getVotes(LocalDate date, Restaurant r) {
        Map<Integer, Long> allRestaurantVotes = getAllRestaurantVotes(date);
        if (allRestaurantVotes == null || !allRestaurantVotes.containsKey(r.getId())) {
            return 0;
        }
        return Math.toIntExact(allRestaurantVotes.get(r.getId()));
    }

    @Override
    public Map<Integer, Long> getAllRestaurantVotes(LocalDate date) {
        Map<Integer, Integer> integerIntegerMap = votes.get(date);
        if (integerIntegerMap != null) {
            return integerIntegerMap.values().stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        }
        return null;
    }
}
