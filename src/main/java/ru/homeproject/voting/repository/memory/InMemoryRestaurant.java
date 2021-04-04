package ru.homeproject.voting.repository.memory;

import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.repository.RestaurantRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryRestaurant implements RestaurantRepository {
    private final AtomicInteger counter = new AtomicInteger();
    private final Map<Integer, Restaurant> map = new ConcurrentHashMap<>();

    @Override
    public Restaurant save(Restaurant restaurant, int userId) {
        if (restaurant.isNew()) {
            restaurant.setId(counter.incrementAndGet());
            map.put(restaurant.getId(), restaurant);
            return restaurant;
        }
        return map.computeIfPresent(restaurant.getId(), (key, old) -> restaurant);
    }


    @Override
    public boolean delete(int id, int userId) {
        return map.remove(id) != null;
    }

    @Override
    public Restaurant get(int id, int userId) {
        return map.get(id);
    }

    @Override
    public List<Restaurant> getAllSorted(int userId) {
        return map.values().stream().sorted(Comparator.comparing(Restaurant::getCreated).reversed()).collect(Collectors.toList());
    }
}
