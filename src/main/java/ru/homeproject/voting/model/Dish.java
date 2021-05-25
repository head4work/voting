package ru.homeproject.voting.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Embeddable
public class Dish {

    @Column(name = "created", nullable = false, columnDefinition = "date default now()")
    @NotNull
    private LocalDate created;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    @Min(value = 1, message = "Should not cost less 1 dollar")
    @Max(value = 100000, message = "Should not be more expensive than 100000")
    private Integer price;

    public Dish() {
    }

    public Dish(@NotNull LocalDate created, String name, Integer price) {
        this.created = created;
        this.name = name;
        this.price = price;
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
