package ru.homeproject.voting.web.vote;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.homeproject.voting.repository.VoteRepository;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
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
