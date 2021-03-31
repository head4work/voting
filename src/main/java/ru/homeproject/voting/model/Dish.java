package ru.homeproject.voting.model;

public class Dish extends AbstractNamedEntity{
    private Integer price;

    public Dish() {
    }

    public Dish(Integer id, String name, Integer price) {
        super(id, name);
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
