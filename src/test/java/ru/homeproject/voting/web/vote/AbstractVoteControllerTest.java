package ru.homeproject.voting.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.homeproject.voting.util.exception.VoteExpiredException;
import ru.homeproject.voting.web.AbstractControllerTest;
import ru.homeproject.voting.web.restaurant.AbstractRestaurantController;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.homeproject.voting.RestaurantTestData.REST1_ID;
import static ru.homeproject.voting.UserTestData.USER_ID;
import static ru.homeproject.voting.web.vote.AbstractVoteController.TIME_UNTIL_VOTE_CAN_BE_CHANGED;

public class AbstractVoteControllerTest extends AbstractControllerTest {
    @Autowired
    private AbstractRestaurantController abstractRestaurantController;

    @Autowired
    private AbstractVoteController controller;

    @Test
    public void vote() {
        boolean timeCheck = LocalDateTime.now().getHour() < TIME_UNTIL_VOTE_CAN_BE_CHANGED;
        controller.saveVote(REST1_ID, USER_ID);
        if (!timeCheck) {
            assertThrows(VoteExpiredException.class, () -> controller.saveVote(REST1_ID + 1, USER_ID));
        } else {
            controller.saveVote(REST1_ID + 1, USER_ID);
        }
        Integer votes = abstractRestaurantController.countVotes(REST1_ID);
        int checkInt = timeCheck ? 1 : 2;
        assertEquals(checkInt, votes.intValue());
    }
}