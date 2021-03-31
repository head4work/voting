package ru.homeproject.voting.to;

import ru.homeproject.voting.model.Dish;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class RestaurantTo {
    private Integer id;

    private LocalDateTime created;

    private List<Dish> menu;

    private Integer votes;

    public RestaurantTo() {
    }

    public RestaurantTo(int id, LocalDateTime created, List<Dish> menu, Integer votes) {
        this.id = id;
        this.created = created;
        this.menu = menu;
        this.votes = votes;
    }

    public Integer getVotes() {
        return votes;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", created=" + created +
                ", menu=" + menu +
                ", votes=" + votes +
                '}';
    }
}
