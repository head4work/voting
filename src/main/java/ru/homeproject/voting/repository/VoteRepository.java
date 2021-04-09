package ru.homeproject.voting.repository;

import ru.homeproject.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.Map;

public interface VoteRepository {

    void saveVote(Restaurant r, int userId);

    Integer getVotes(LocalDate date, int restId);

    //  boolean deleteVote(LocalDate date, int userId);

    Map<Integer, Long> getAllRestaurantVotes(LocalDate date);
}
