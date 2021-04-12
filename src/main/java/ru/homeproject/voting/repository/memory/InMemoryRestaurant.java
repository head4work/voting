package ru.homeproject.voting.repository.memory;

import org.springframework.stereotype.Repository;
import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.repository.RestaurantRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryRestaurant implements RestaurantRepository {
    private final AtomicInteger counter = new AtomicInteger();
    private final Map<Integer, Restaurant> map = new ConcurrentHashMap<>();

    @Override
    public Restaurant save(Restaurant r) {
        if (r.isNew()) {
            r.setId(counter.incrementAndGet());
            map.putIfAbsent(r.id(), r);
            return r;
        }
        return map.computeIfPresent(r.id(), (integer, restaurant) -> r);
    }

    @Override
    public boolean delete(int id) {
        return map.remove(id) != null;
    }

    @Override
    public Restaurant get(int id) {
        return map.get(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return map.values().stream().sorted(Comparator.comparing(Restaurant::getCreated).reversed()).collect(Collectors.toList());
    }
}
