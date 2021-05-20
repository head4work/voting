package ru.homeproject.voting.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.homeproject.voting.model.Vote;
import ru.homeproject.voting.repository.VoteRepository;
import ru.homeproject.voting.web.SecurityUtil;

public abstract class AbstractVoteController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final VoteRepository vote;

    public AbstractVoteController(VoteRepository vote) {
        this.vote = vote;
    }

    public Vote vote(int restId) {
        log.info("vote {}", restId);
        int userId = SecurityUtil.authUserId();
        return vote.saveVote(restId, userId);
    }

}
