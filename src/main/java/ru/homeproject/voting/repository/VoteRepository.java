package ru.homeproject.voting.repository;

import java.time.LocalDate;
import java.util.Map;

public interface VoteRepository {

    void saveVote(int restId, int userId);

    Integer getVotes(LocalDate date, int restId);

    //  boolean deleteVote(LocalDate date, int userId);

    Map<Integer, Long> getAllRestaurantVotes(LocalDate date);
}
