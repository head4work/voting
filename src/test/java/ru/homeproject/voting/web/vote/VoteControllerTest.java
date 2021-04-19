package ru.homeproject.voting.web.vote;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.homeproject.voting.web.AbstractControllerTest;
import ru.homeproject.voting.web.restaurant.RestaurantRestController;

import static ru.homeproject.voting.RestaurantTestData.REST1_ID;

public class VoteControllerTest extends AbstractControllerTest {
    private RestaurantRestController restaurantRestController;

    @Autowired
    private VoteRestController controller;

    @Test
    public void vote() {
        controller.vote(REST1_ID);
    }
}