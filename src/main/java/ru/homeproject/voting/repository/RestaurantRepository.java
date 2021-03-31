package ru.homeproject.voting.repository;

import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.model.User;

import java.util.List;

public interface RestaurantRepository {
    // null if not found, when updated
   Restaurant save(Restaurant restaurant, int userId);

    // false if not found
    boolean delete(int id,int userId);

    // null if not found
    Restaurant get(int id,int userId);

    //sorted by date desc
    List<Restaurant> getAllSorted(int userId);
}
