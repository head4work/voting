package ru.homeproject.voting.web.vote;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.homeproject.voting.repository.datajpa.CrudRestaurantRepository;
import ru.homeproject.voting.repository.datajpa.CrudUserRepository;
import ru.homeproject.voting.repository.datajpa.CrudVoteRepository;
import ru.homeproject.voting.util.exception.VoteExpiredException;

@RestController
@RequestMapping(value = VoteRestController.REST_URL)
public class VoteRestController extends AbstractVoteController {

    protected static final String REST_URL = "/rest/vote";

    public VoteRestController(CrudRestaurantRepository crudRestaurantRepository, CrudUserRepository crudUserRepository, CrudVoteRepository crudVoteRepository) {
        super(crudRestaurantRepository, crudUserRepository, crudVoteRepository);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createVote(@RequestParam int id) {
        try {
            super.vote(id);
        } catch (VoteExpiredException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Your vote right has been expired", e);
        }
    }
}
