package ru.homeproject.voting.web.restaurant;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.to.RestaurantTo;
import ru.homeproject.voting.util.exception.NotFoundException;
import ru.homeproject.voting.web.AbstractControllerTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static ru.homeproject.voting.RestaurantTestData.*;


public class RestaurantControllerTest extends AbstractControllerTest {

    @Autowired
    private RestaurantRestController controller;

    @Test
    public void create() {
        Restaurant created = controller.create(getNew());
        int newId = created.id();
        Restaurant newRest = getNew();
        newRest.setId(newId);
        assertEquals(created, newRest);
        assertEquals(controller.get(newId), newRest);
    }

    @Test
    public void update() {
        Restaurant updated = getUpdated();
        controller.update(updated, updated.id());
        assertEquals(controller.get(REST1_ID), getUpdated());
    }

    @Test
    public void get() {
        Restaurant restaurant = controller.get(REST1_ID);
        assertEquals(restaurant, rest1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> controller.get(NOT_FOUND));
    }

    @Test
    public void delete() {
        controller.delete(REST1_ID);
        assertThrows(NotFoundException.class, () -> controller.get(REST1_ID));
    }

    @Test
    public void deleteNotFound() {
        Assert.assertThrows(NotFoundException.class, () -> controller.delete(NOT_FOUND));
    }

    @Test
    public void getAll() {
        List<Restaurant> all = controller.getAll();
        List<Restaurant> expected = Arrays.asList(rest1, rest2);
        assertEquals(all, expected);
    }

    @Test
    public void getAllSortedByVotes() {
        List<RestaurantTo> sortedByVotes = controller.getAllSortedByVotes();
        RESTAURANT_TEST_MATCHER.assertMatch(sortedByVotes, getSortedByVotes());
    }
}