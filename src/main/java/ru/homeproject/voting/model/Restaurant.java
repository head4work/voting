package ru.homeproject.voting.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT r FROM Restaurant r LEFT JOIN FETCH r.menu ORDER BY r.created, r.name"),
})
@Entity
@Table(name = "RESTAURANTS")
public class Restaurant extends AbstractNamedEntity {

    public static final String DELETE = "Restaurant.delete";
    public static final String ALL_SORTED = "Restaurant.getAll";

    @Column(name = "CREATED", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDateTime created;

    @ElementCollection // (fetch = FetchType.EAGER)
    @CollectionTable(
            name = "DISHES",
            joinColumns = @JoinColumn(name = "RESTAURANT_ID")
    )
    private List<Dish> menu = new ArrayList<>();

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, LocalDateTime created, List<Dish> menu) {
        super(id, name);
        this.created = created;
        this.menu = menu;
    }

    public Restaurant(Integer id, String name, LocalDateTime created, Dish... dishes) {
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
