package ru.homeproject.voting.web.vote;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.homeproject.voting.repository.VoteRepository;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController extends AbstractVoteController {
    protected static final String REST_URL = "rest/votes";

    public VoteRestController(VoteRepository vote) {
        super(vote);
    }

    @Override
    public void vote(int restId) {
        super.vote(restId);
    }

}
