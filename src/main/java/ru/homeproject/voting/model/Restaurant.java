package ru.homeproject.voting.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Restaurant extends AbstractNamedEntity{
    private Date created;

    private List<Dish> menu;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, Date created, List<Dish> menu) {
        super(id, name);
        this.created = created;
        this.menu = menu;
    }

    public Restaurant(Integer id, String name, Date created, Dish ... dishes) {
        super(id, name);
        this.created = created;
        this.menu = Arrays.asList(dishes);
    }
}
