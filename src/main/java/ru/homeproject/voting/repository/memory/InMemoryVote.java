package ru.homeproject.voting.repository.memory;

import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.model.Vote;
import ru.homeproject.voting.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryVote implements VoteRepository {
    // user_id   date,restaurant_id
    private final static Map<Integer, Map<LocalDate, Integer>> votes = new ConcurrentHashMap<>();

    @Override
    public void saveVote(Restaurant r, int userId) {
        if (!votes.containsKey(userId) || !votes.get(userId).containsKey(LocalDate.now())) {
            insertVote(r, userId);
        } else if (LocalDateTime.now().getHour() < 20) {
            votes.get(userId).computeIfPresent(LocalDate.now(), (localDate, integer) -> r.getId());
        } else {
            System.out.println("already voted");
        }
    }

    private void insertVote(Restaurant r, int userId) {
        Map<LocalDate, Integer> vote = new ConcurrentHashMap<>();
        vote.put(LocalDate.now(), r.getId());
        votes.put(userId, vote);
    }

    @Override
    public Vote getVote(LocalDate date, int userId) {
        return null;
    }

    @Override
    public boolean deleteVote(LocalDate date, int userId) {
      return   votes.get(userId).remove(date) != null;
    }



    @Override
    public Map<Integer, Long> getAllRestaurantVotes(LocalDate date) {
        return votes.values().stream()
                //   .filter(localDateIntegerMap -> localDateIntegerMap.containsKey(date))
                .map(new Function<Map<LocalDate, Integer>, Integer>() {
                    @Override
                    public Integer apply(Map<LocalDate, Integer> localDateIntegerMap) {
                        return localDateIntegerMap.get(date);
                    }
                })

                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
