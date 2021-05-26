package ru.homeproject.voting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity {

    @Column(name = "created", nullable = false, columnDefinition = "date default now()")
    @NotNull
    private LocalDate created;

    @NotNull
    @Min(value = 1, message = "Should not cost less 1 dollar")
    @Max(value = 100000, message = "Should not be more expensive than 100000")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(@NotNull LocalDate created, String name, Integer price) {
        this.created = created;
        this.name = name;
        this.price = price;
    }

    @JsonBackReference
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "created=" + created +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
