package ru.homeproject.voting.web.vote;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.homeproject.voting.repository.VoteRepository;

@RestController
@RequestMapping(value = VoteRestController.REST_URL)
public class VoteRestController extends AbstractVoteController {
    protected static final String REST_URL = "/rest/vote";

    public VoteRestController(VoteRepository vote) {
        super(vote);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createVote(@RequestParam int id) {
        super.vote(id);
    }
}
