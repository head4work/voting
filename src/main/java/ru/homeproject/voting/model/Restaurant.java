package ru.homeproject.voting.model;

import java.util.Arrays;
import java.util.List;

public class Restaurant extends AbstractNamedEntity{
    private List<Dish> menu;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, List<Dish> menu) {
        super(id, name);
        this.menu = menu;
    }

    public Restaurant(Integer id, String name, Dish ... dishes) {
        super(id, name);
        this.menu = Arrays.asList(dishes);
    }
}
