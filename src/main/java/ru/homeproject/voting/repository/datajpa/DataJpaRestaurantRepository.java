package ru.homeproject.voting.repository.datajpa;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.homeproject.voting.model.Restaurant;

import java.util.List;

@Repository
public class DataJpaRestaurantRepository {
    private final CrudRestaurantRepository crudRestaurantRepository;
    private static final Sort SORT_CREATED = Sort.by(Sort.Direction.DESC, "created");

    public DataJpaRestaurantRepository(CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant save(Restaurant restaurant) {
        return crudRestaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public boolean delete(int id) {
        return crudRestaurantRepository.delete(id) != 0;
    }

    public Restaurant get(int id) {
        return crudRestaurantRepository.findById(id).orElse(null);
    }

    @Cacheable("restaurants")
    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll(SORT_CREATED);
        // return getAllWithMenu();
    }

    public List<Restaurant> getAllWithMenu() {
        return crudRestaurantRepository.getAllWithMenu();
    }
}
