package ru.homeproject.voting.web.vote;

import ru.homeproject.voting.repository.VoteRepository;

public class VoteRestController extends AbstractVoteController {
    public VoteRestController(VoteRepository vote) {
        super(vote);
    }

    @Override
    public void vote(int restId) {
        super.vote(restId);
    }

}
