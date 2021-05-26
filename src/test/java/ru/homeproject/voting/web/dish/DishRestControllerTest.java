package ru.homeproject.voting.web.dish;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.homeproject.voting.RestaurantTestData;
import ru.homeproject.voting.model.Dish;
import ru.homeproject.voting.repository.datajpa.CrudDishRepository;
import ru.homeproject.voting.web.AbstractRestControllerTest;
import ru.homeproject.voting.web.json.JsonUtil;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.homeproject.voting.RestaurantTestData.*;
import static ru.homeproject.voting.TestUtil.readFromJson;
import static ru.homeproject.voting.UserTestData.ADMIN;

class DishRestControllerTest extends AbstractRestControllerTest {
    private static final String REST_URL = DishRestController.REST_URL + '/';

    @Autowired
    private CrudDishRepository repository;

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL).with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_TEST_MATCHER.contentJson(getDishesList()));
    }

    @Test
    void createWithLocation() throws Exception {
        Dish newDish = RestaurantTestData.getNew().getMenu().get(0);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)).with(userHttpBasic(ADMIN)))
                .andExpect(status().isCreated());

        Dish created = readFromJson(action, Dish.class);
        int newId = created.id();
        newDish.setId(newId);
        DISH_TEST_MATCHER.assertMatch(created, newDish);
        DISH_TEST_MATCHER.assertMatch(repository.findById(newId).orElse(null), newDish);
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + DISH1_ID).with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_TEST_MATCHER.contentJson(rest1.getMenu().get(0)));
    }

    @Test
    void update() throws Exception {
        Dish updated = RestaurantTestData.getUpdated().getMenu().get(0);
        perform(MockMvcRequestBuilders.put(REST_URL + DISH1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)).with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        DISH_TEST_MATCHER.assertMatch(repository.findById(DISH1_ID).orElse(null), updated);
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + DISH1_ID).with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NoSuchElementException.class, () -> repository.findById(DISH1_ID).orElseThrow());
    }

}