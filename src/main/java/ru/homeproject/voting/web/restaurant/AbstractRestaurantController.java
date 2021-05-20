package ru.homeproject.voting.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.repository.datajpa.DataJpaRestaurantRepository;
import ru.homeproject.voting.repository.datajpa.DataJpaVoteRepository;
import ru.homeproject.voting.to.RestaurantTo;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.homeproject.voting.util.ValidationUtil.*;


public abstract class AbstractRestaurantController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final DataJpaRestaurantRepository repository;
    private final DataJpaVoteRepository vote;

    public AbstractRestaurantController(DataJpaRestaurantRepository repository, DataJpaVoteRepository vote) {
        this.repository = repository;
        this.vote = vote;
    }

    public Restaurant create(Restaurant r) {
        log.info("create {}", r);
        checkNew(r);
        Assert.notNull(r, "Restaurant must not be null");
        return repository.save(r);
    }

    public Restaurant get(int id) {
        log.info("get {}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    public void update(Restaurant r, int id) {
        log.info("update {}", id);
        assureIdConsistent(r, id);
        Assert.notNull(r, "Restaurant must not be null");
        checkNotFoundWithId(repository.save(r), r.getId());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    public List<Restaurant> getAll() {
        log.info("getAll");
        return repository.getAll();
    }

    public List<RestaurantTo> getAllSortedByVotes() {
        return sortByVotes(repository.getAll());
    }

    private List<RestaurantTo> sortByVotes(List<Restaurant> list) {
        return list.stream().map(this::castTo)
                .sorted(Comparator.comparing(RestaurantTo::getVotes).reversed())
                .collect(Collectors.toList());
    }

    private RestaurantTo castTo(Restaurant r) {
        return new RestaurantTo(r.getName(), r.getId(), r.getCreated(), r.getMenu(),
                countVotes(r.getId()));
    }

    public Integer countVotes(int r) {
        log.info("countVotes {}", r);
        return vote.getVotes(LocalDate.now(), r);
    }
}
