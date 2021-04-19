package ru.homeproject.voting.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@NamedQueries({
        @NamedQuery(name = Vote.BY_DATE, query = "SELECT v FROM Vote v WHERE v.created=:created"),
        @NamedQuery(name = Vote.BY_RESTAURANT, query = "SELECT v FROM Vote v WHERE v.created=:created and v.restaurant.id=:restaurant"),
})

@Entity
@Table(name = "VOTES")
public class Vote extends AbstractBaseEntity {

    public static final String BY_DATE = "Vote.getByDate";
    public static final String BY_RESTAURANT = "Vote.byRestaurant";

    @Column(name = "CREATED", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDate created;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID")
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
