package ru.homeproject.voting.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.*;

@Embeddable
public class Dish {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    @Min(value = 1, message = "Should not cost less 1 dollar")
    @Max(value = 100000, message = "Should not be more expensive than 100000")
    private Integer price;

    public Dish() {
    }

    public Dish(String name, Integer price) {
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

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
