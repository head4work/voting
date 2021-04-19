package ru.homeproject.voting;

import ru.homeproject.voting.model.Dish;
import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.to.RestaurantTo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ru.homeproject.voting.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final ru.homeproject.voting.TestMatcher<RestaurantTo> RESTAURANT_TEST_MATCHER = ru.homeproject.voting.TestMatcher
            .usingIgnoringFieldsComparator("created");

    public static final int REST1_ID = START_SEQ + 2;
    public static final int NOT_FOUND = 10;
    public static final Restaurant rest1 = new Restaurant(REST1_ID, "name1", LocalDateTime.now(), new Dish("soup", 500));
    public static final Restaurant rest2 = new Restaurant(REST1_ID + 1, "name2", LocalDateTime.now(), new Dish("rice", 500),
            new Dish("chicken", 700), new Dish("spaghetti", 300));

    public static List<RestaurantTo> getSortedByVotes() {
        List<RestaurantTo> list = new ArrayList<>();
        list.add(new RestaurantTo(rest1.getName(), rest1.getId(), rest1.getCreated(), rest1.getMenu(), 0));
        list.add(new RestaurantTo(rest2.getName(), rest2.getId(), rest2.getCreated(), rest2.getMenu(), 0));
        list.sort(Comparator.comparing(RestaurantTo::getVotes));
        return list;
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "new", LocalDateTime.now(), new Dish("new", 100));
    }

    public static Restaurant getUpdated() {
        return new Restaurant(REST1_ID, rest1.getName(), rest1.getCreated(), new Dish("new", 1000));
    }


}
