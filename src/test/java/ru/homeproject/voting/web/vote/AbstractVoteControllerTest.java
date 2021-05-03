package ru.homeproject.voting.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.homeproject.voting.repository.VoteRepository;
import ru.homeproject.voting.web.AbstractControllerTest;
import ru.homeproject.voting.web.SecurityUtil;
import ru.homeproject.voting.web.restaurant.AbstractRestaurantController;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.homeproject.voting.RestaurantTestData.REST1_ID;
import static ru.homeproject.voting.repository.datajpa.DataJpaVoteRepository.TIME_UNTIL_VOTE_CAN_BE_CHANGED;

public class AbstractVoteControllerTest extends AbstractControllerTest {
    @Autowired
    private AbstractRestaurantController abstractRestaurantController;

    @Autowired
    private VoteRepository repository;

    @Test
    public void vote() {
        repository.saveVote(REST1_ID, SecurityUtil.authUserId());
        repository.saveVote(REST1_ID, SecurityUtil.authUserId());
        repository.saveVote(REST1_ID + 1, SecurityUtil.authUserId());
        System.out.println("---------------------------------------------");
        Integer votes = abstractRestaurantController.countVotes(REST1_ID);
        int checkInt = LocalDateTime.now().getHour() < TIME_UNTIL_VOTE_CAN_BE_CHANGED ? 1 : 2;
        assertEquals(checkInt, votes.intValue());
    }
}