package ru.homeproject.voting.web.restaurant;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.repository.RestaurantRepository;
import ru.homeproject.voting.repository.VoteRepository;
import ru.homeproject.voting.to.RestaurantTo;
import ru.homeproject.voting.web.SecurityUtil;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.homeproject.voting.util.ValidationUtil.*;

@Controller
public class RestaurantRestController {

    private final RestaurantRepository repository;
    private final VoteRepository vote;

    public RestaurantRestController(RestaurantRepository repository, VoteRepository vote) {
        this.repository = repository;
        this.vote = vote;
    }

    public Restaurant create(Restaurant r) {
        int userId = SecurityUtil.authUserId();
        checkNew(r);
        Assert.notNull(r, "Restaurant must not be null");
        return repository.save(r, userId);
    }

    public Restaurant get(int id) {
        int userId = SecurityUtil.authUserId();
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public void update(Restaurant r, int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(r, id);
        Assert.notNull(r, "Restaurant must not be null");
        checkNotFoundWithId(repository.save(r, userId), r.id());
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public List<RestaurantTo> getAll() {
        int userId = SecurityUtil.authUserId();
        return getSortedByVotes(repository.getAllSorted(userId));
    }

    private List<RestaurantTo> getSortedByVotes(List<Restaurant> list) {
        return list.stream().map(this::castTo)
                .sorted(Comparator.comparing(RestaurantTo::getVotes).reversed())
                .collect(Collectors.toList());
    }

    private RestaurantTo castTo(Restaurant r) {
        return new RestaurantTo(r.getName(), r.getId(), r.getCreated(), r.getMenu(),
                countVotes(r));
    }

    private Integer countVotes(Restaurant r) {
        return vote.getVotes(LocalDate.now(), r.id());
    }
}
