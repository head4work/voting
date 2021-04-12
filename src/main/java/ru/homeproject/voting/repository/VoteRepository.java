package ru.homeproject.voting.repository;

import ru.homeproject.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.Map;

public interface VoteRepository {

    void saveVote(int restId, int userId);

    Integer getVotes(LocalDate date, Restaurant r);

    //  boolean deleteVote(LocalDate date, int userId);

    Map<Integer, Long> getAllRestaurantVotes(LocalDate date);
}
