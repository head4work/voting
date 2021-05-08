package ru.homeproject.voting.util;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.homeproject.voting.model.Dish;
import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.model.Role;
import ru.homeproject.voting.model.User;
import ru.homeproject.voting.web.restaurant.AbstractRestaurantController;
import ru.homeproject.voting.web.vote.AbstractVoteController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class SpringMain {
  private static final User NEW_USER = new User(null, "name", "name@email.com", "password", true, new Date(), Collections.singleton(Role.USER));

  public static void main(String[] args) {
    try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
        System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
        AbstractRestaurantController abstractRestaurantController = appCtx.getBean(AbstractRestaurantController.class);
        AbstractVoteController abstractVoteController = appCtx.getBean(AbstractVoteController.class);
        abstractRestaurantController.create(new Restaurant(null, "name", LocalDateTime.now(), new Dish("name", 500)));
        abstractRestaurantController.create(new Restaurant(null, "name", LocalDateTime.now(), new Dish("name1", 500)));
        abstractRestaurantController.update(new Restaurant(100002, "newName", LocalDateTime.now(),
                new Dish("soup", 700),
                new Dish("rice", 500),
                new Dish("pasta", 700)), 100002);
        abstractVoteController.vote(100003);
        abstractVoteController.vote(100002);
        abstractVoteController.voteByAdmin(100003);
        System.out.println("-------------------------------------------------------------");

        abstractRestaurantController.getAll().forEach(System.out::println);
        // voteRestController.voteByAdmin(100002);
        abstractRestaurantController.getAllSortedByVotes().forEach(System.out::println);
        // restaurantRestController.delete(100002);
        // restaurantRestController.getAll().forEach(System.out::println);



      /*AdminRestController adminRestController = appCtx.getBean(AdminRestController.class);
      adminRestController.getAll().forEach(System.out::println);

      adminRestController.create(NEW_USER);
      adminRestController.getAll().forEach(System.out::println);*/


    }

  }
}
