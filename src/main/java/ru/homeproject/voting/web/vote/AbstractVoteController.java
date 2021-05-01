package ru.homeproject.voting.web.vote;

import ru.homeproject.voting.repository.VoteRepository;
import ru.homeproject.voting.web.SecurityUtil;


public abstract class AbstractVoteController {
    private final VoteRepository vote;

    public AbstractVoteController(VoteRepository vote) {
        this.vote = vote;
    }

    public void vote(int restId) {
        int userId = SecurityUtil.authUserId();
        vote.saveVote(restId, userId);
    }

    // Test methods
    public void voteByAdmin(int restId) {
        int userId = 100001;
        vote.saveVote(restId, userId);
    }

}
