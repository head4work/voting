package ru.homeproject.voting.model;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;

@Table(name = "VOTES", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "created"},
        name = "vote_unique_user_date_idx")})
public class Vote {
    private LocalDate created;
    private User user;
    private Restaurant restaurant;

    public Vote() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }
}
