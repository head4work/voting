package ru.homeproject.voting.web.vote;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.repository.VoteRepository;
import ru.homeproject.voting.web.SecurityUtil;

@Controller
public class VoteRestController {
    private final VoteRepository vote;

    public VoteRestController(VoteRepository vote) {
        this.vote = vote;
    }

    public void vote(Restaurant r) {
        int userId = SecurityUtil.authUserId();
        Assert.notNull(r, "Restaurant must not be null");
        vote.saveVote(r, userId);
    }


    // Test methods
    public void voteByUserTwo(Restaurant r) {
        int userId = 100001;
        Assert.notNull(r, "Restaurant must not be null");
        vote.saveVote(r, userId);
    }

}
