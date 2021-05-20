package ru.homeproject.voting.web.vote;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.homeproject.voting.repository.datajpa.DataJpaVoteRepository;
import ru.homeproject.voting.util.exception.VoteExpiredException;

@RestController
@RequestMapping(value = VoteRestController.REST_URL)
public class VoteRestController extends AbstractVoteController {

    protected static final String REST_URL = "/rest/vote";

    public VoteRestController(DataJpaVoteRepository vote) {
        super(vote);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createVote(@RequestParam int id) {
        try {
            super.vote(id);
        } catch (VoteExpiredException e) {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Your vote right has been expired", e);
        }
    }
}
