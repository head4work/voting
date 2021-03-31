package ru.homeproject.voting.model;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

public class Restaurant extends AbstractNamedEntity{
    private LocalDateTime created;

    private List<Dish> menu;

    private Set<Integer> votedUsers = new ConcurrentSkipListSet<>();

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, LocalDateTime created, List<Dish> menu) {
        super(id, name);
        this.created = created;
        this.menu = menu;
    }

    public Restaurant(Integer id, String name, LocalDateTime created, Dish ... dishes) {
        super(id, name);
        this.created = created;
        this.menu = Arrays.asList(dishes);
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

    public Set<Integer> getVotedUsers() {
        return votedUsers;
    }

    public void setVotedUsers(Set<Integer> votedUsers) {
        this.votedUsers = votedUsers;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created=" + created +
                ", menu=" + menu +
                '}';
    }
}
