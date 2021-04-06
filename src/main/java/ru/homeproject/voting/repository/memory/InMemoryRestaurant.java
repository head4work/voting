package ru.homeproject.voting.repository.memory;

import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.repository.RestaurantRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryRestaurant implements RestaurantRepository {
    private final AtomicInteger counter = new AtomicInteger();
    private final Map<Integer, Map<Integer, Restaurant>> map = new ConcurrentHashMap<>();

    @Override
    public Restaurant save(Restaurant r, int userId) {
        if (r.isNew()) {
            r.setId(counter.incrementAndGet());
            Map<Integer, Restaurant> integerRestaurantMap = map.computeIfAbsent(userId, integer -> new ConcurrentHashMap<>());
            integerRestaurantMap.put(r.getId(), r);
            map.put(userId, integerRestaurantMap);
            return r;
        }
        return map.get(userId).computeIfPresent(r.getId(), (integer, restaurant) -> r);
    }

    @Override
    public boolean delete(int id, int userId) {
        return map.get(userId).remove(id) != null;
    }

    @Override
    public Restaurant get(int id, int userId) {
        return map.get(userId).get(id);
    }

    @Override
    public List<Restaurant> getAllSorted(int userId) {
        return map.get(userId).values().stream().sorted(Comparator.comparing(Restaurant::getCreated).reversed()).collect(Collectors.toList());
    }
}
