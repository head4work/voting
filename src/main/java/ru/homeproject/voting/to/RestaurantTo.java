package ru.homeproject.voting.to;

import ru.homeproject.voting.model.Dish;

import java.time.LocalDateTime;
import java.util.List;

public class RestaurantTo {
    private String name;
    private Integer id;

    private LocalDateTime created;

    private List<Dish> menu;

    private Integer votes;

    public RestaurantTo() {
    }

    public RestaurantTo(String name, int id, LocalDateTime created, List<Dish> menu, Integer votes) {
        this.name = name;
        this.id = id;
        this.created = created;
        this.menu = menu;
        this.votes = votes;
    }

    public Integer getVotes() {
        return votes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", created=" + created +
                ", menu=" + menu +
                ", votes=" + votes +
                '}';
    }
}
