package ru.homeproject.voting.repository;

import ru.homeproject.voting.model.Vote;

import java.time.LocalDate;

public interface VoteRepository {

    Vote saveVote(int restId, int userId);

    Integer getVotes(LocalDate date, int restId);

}
