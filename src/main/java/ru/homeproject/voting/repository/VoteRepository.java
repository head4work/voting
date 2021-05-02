package ru.homeproject.voting.repository;

import ru.homeproject.voting.model.Vote;

import java.time.LocalDate;
import java.util.Map;

public interface VoteRepository {

    Vote saveVote(int restId, int userId);

    Integer getVotes(LocalDate date, int restId);

    //  boolean deleteVote(LocalDate date, int userId);

    Map<Integer, Long> getAllRestaurantVotes(LocalDate date);
}
