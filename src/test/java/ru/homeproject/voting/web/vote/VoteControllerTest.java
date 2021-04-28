package ru.homeproject.voting.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.homeproject.voting.web.AbstractControllerTest;
import ru.homeproject.voting.web.restaurant.RestaurantRestController;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.homeproject.voting.RestaurantTestData.REST1_ID;

public class VoteControllerTest extends AbstractControllerTest {
    @Autowired
    private RestaurantRestController restaurantRestController;

    @Autowired
    private VoteRestController controller;

    @Test
    public void vote() {
        controller.vote(REST1_ID);
        controller.vote(REST1_ID);
        controller.vote(REST1_ID + 1);
        System.out.println("---------------------------------------------");
        Integer votes = restaurantRestController.countVotes(REST1_ID);
        int checkInt = LocalDateTime.now().getHour() < 11 ? 0 : 1;
        assertEquals(checkInt, votes.intValue());
    }
}