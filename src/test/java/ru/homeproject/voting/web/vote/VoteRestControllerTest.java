package ru.homeproject.voting.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.homeproject.voting.RestaurantTestData;
import ru.homeproject.voting.web.AbstractRestControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VoteRestControllerTest extends AbstractRestControllerTest {

    private static final String REST_URL = VoteRestController.REST_URL;

    @Test
    void createVote() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL).param("id", String.valueOf(RestaurantTestData.REST1_ID)))
                .andExpect(status().isOk());
    }
}