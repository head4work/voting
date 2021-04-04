package ru.homeproject.voting.repository;

import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.model.Vote;

import java.time.LocalDate;
import java.util.Map;

public interface VoteRepository {
    void saveVote(Restaurant r, int userId);

    Vote getVote(LocalDate date, int userId);

    boolean deleteVote(LocalDate date, int userId);

    Map<Integer, Long> getAllRestaurantVotes(LocalDate date);
}
