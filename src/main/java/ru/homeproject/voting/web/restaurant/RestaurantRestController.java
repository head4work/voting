package ru.homeproject.voting.web.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.repository.RestaurantRepository;
import ru.homeproject.voting.repository.VoteRepository;
import ru.homeproject.voting.to.RestaurantTo;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController extends AbstractRestaurantController {
    protected static final String REST_URL = "/rest/admin/restaurants";

    public RestaurantRestController(RestaurantRepository repository, VoteRepository vote) {
        super(repository, vote);
    }

    @PostMapping(value = REST_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant rest) {
        Restaurant created = super.create(rest);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @GetMapping(value = REST_URL + "/{id}")
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @PutMapping(value = REST_URL + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant r, @PathVariable int id) {
        super.update(r, id);
    }

    @Override
    @DeleteMapping(value = REST_URL + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping(value = REST_URL)
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/rest/restaurants")
    public List<RestaurantTo> getAllSortedByVotes() {
        return super.getAllSortedByVotes();
    }

    @Override
    @GetMapping(value = REST_URL + "/votes")
    public Integer countVotes(@RequestParam int id) {
        return super.countVotes(id);
    }
}
