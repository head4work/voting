package ru.homeproject.voting.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.homeproject.voting.RestaurantTestData;
import ru.homeproject.voting.web.AbstractRestControllerTest;
import ru.homeproject.voting.web.restaurant.AbstractRestaurantController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.homeproject.voting.RestaurantTestData.REST1_ID;
import static ru.homeproject.voting.UserTestData.ADMIN;
import static ru.homeproject.voting.UserTestData.USER;

@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class VoteRestControllerTest extends AbstractRestControllerTest {

    private static final String REST_URL = VoteRestController.REST_URL;

    @Autowired
    private AbstractRestaurantController controller;

    @Test
    void createVote() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL).param("id", String.valueOf(RestaurantTestData.REST1_ID))
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk());
        assertEquals(2, controller.countVotes(REST1_ID));
    }

    @Test
    void createVoteDuplicate() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL).param("id", String.valueOf(RestaurantTestData.REST1_ID))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity());
    }
}