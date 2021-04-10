package ru.homeproject.voting.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.repository.VoteRepository;

import java.time.LocalDate;
import java.util.Map;

@Repository
public class JpaVoteRepository implements VoteRepository {
    @Override
    public void saveVote(Restaurant r, int userId) {

    }

    @Override
    public Integer getVotes(LocalDate date, int restId) {
        return null;
    }

    @Override
    public Map<Integer, Long> getAllRestaurantVotes(LocalDate date) {
        return null;
    }
}
