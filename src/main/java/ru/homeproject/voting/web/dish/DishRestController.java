package ru.homeproject.voting.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.homeproject.voting.model.Dish;
import ru.homeproject.voting.repository.datajpa.CrudDishRepository;

import java.net.URI;
import java.util.List;

import static ru.homeproject.voting.util.ValidationUtil.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected static final String REST_URL = "/rest/admin/restaurants/dishes";
    private final CrudDishRepository repository;

    public DishRestController(CrudDishRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = REST_URL)
    public List<Dish> getAll() {
        log.info("get all");
        return repository.findAll();
    }

    @PostMapping(value = REST_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish) {
        log.info("create {}", dish);
        checkNew(dish);
        Assert.notNull(dish, "Dish must not be null");
        Dish created = repository.save(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = REST_URL + "/{id}")
    public Dish get(@PathVariable int id) {
        log.info("get {}", id);
        return repository.findById(id).orElse(null);
    }

    @PutMapping(value = REST_URL + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable int id) {
        log.info("update {}", id);
        assureIdConsistent(dish, id);
        Assert.notNull(dish, "Restaurant must not be null");
        checkNotFoundWithId(repository.save(dish), dish.getId());
    }

    @DeleteMapping(value = REST_URL + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @GetMapping(value = "/rest/admin/restaurants/" + "{id}" + "/dishes")
    public List<Dish> getAllByRestaurant(@PathVariable int id) {
        log.info("get all by restaurant {}", id);
        return repository.getAllByRestaurant(id);
    }

}
