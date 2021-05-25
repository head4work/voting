package ru.homeproject.voting.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;
import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.repository.datajpa.CrudRestaurantRepository;
import ru.homeproject.voting.to.RestaurantTo;
import ru.homeproject.voting.web.vote.AbstractVoteController;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.homeproject.voting.util.ValidationUtil.*;

public abstract class AbstractRestaurantController {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected final CrudRestaurantRepository crudRestaurantRepository;
    private static final Sort SORT_CREATED = Sort.by(Sort.Direction.DESC, "created");
    private final AbstractVoteController vote;

    public AbstractRestaurantController(CrudRestaurantRepository crudRestaurantRepository, AbstractVoteController vote) {
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.vote = vote;
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant create(Restaurant r) {
        log.info("create {}", r);
        checkNew(r);
        Assert.notNull(r, "Restaurant must not be null");
        return crudRestaurantRepository.save(r);
    }

    public Restaurant get(int id) {
        log.info("get {}", id);
        return checkNotFoundWithId(crudRestaurantRepository.findById(id).orElse(null), id);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void update(Restaurant r, int id) {
        log.info("update {}", id);
        assureIdConsistent(r, id);
        Assert.notNull(r, "Restaurant must not be null");
        checkNotFoundWithId(crudRestaurantRepository.save(r), r.getId());
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int id) {
        log.info("delete {}", id);
        checkNotFoundWithId(crudRestaurantRepository.delete(id) != 0, id);
    }

    @Cacheable("restaurants")
    public List<Restaurant> getAll() {
        log.info("getAll");
        return crudRestaurantRepository.findAll(SORT_CREATED);
    }

    public List<RestaurantTo> getAllSortedByVotes() {
        return sortByVotes(getAll());
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
