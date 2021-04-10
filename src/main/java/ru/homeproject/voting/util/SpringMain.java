package ru.homeproject.voting.util;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.homeproject.voting.model.Role;
import ru.homeproject.voting.model.User;
import ru.homeproject.voting.web.restaurant.RestaurantRestController;
import ru.homeproject.voting.web.user.AdminRestController;
import ru.homeproject.voting.web.vote.VoteRestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class SpringMain {
  private static final User NEW_USER = new User(null, "name", "name@email.com", "password", true, new Date(), Collections.singleton(Role.USER));

  public static void main(String[] args) {
    try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/appconfig.xml")) {
      System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
      RestaurantRestController restaurantRestController = appCtx.getBean(RestaurantRestController.class);
      VoteRestController voteRestController = appCtx.getBean(VoteRestController.class);
     /* restaurantRestController.create(new Restaurant(null, "name", LocalDateTime.now(), new Dish(null, "name", 500)));
      restaurantRestController.create(new Restaurant(null, "name", LocalDateTime.now(), new Dish(null, "name1", 500)));
      restaurantRestController.getAll().forEach(System.out::println);
      voteRestController.vote(restaurantRestController.get(1));
      voteRestController.vote(restaurantRestController.get(2));
      restaurantRestController.getAll().forEach(System.out::println);*/

      AdminRestController adminRestController = appCtx.getBean(AdminRestController.class);
      adminRestController.getAll().forEach(System.out::println);

      adminRestController.create(NEW_USER);
      adminRestController.getAll().forEach(System.out::println);


    }

  }


/* try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/inmemory.xml")) {
        System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
        AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
        adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));
        System.out.println();

        MealRestController mealController = appCtx.getBean(MealRestController.class);
        List<MealTo> filteredMealsWithExcess =
                mealController.getBetween(
                        LocalDate.of(2020, Month.JANUARY, 30), LocalTime.of(7, 0),
                        LocalDate.of(2020, Month.JANUARY, 31), LocalTime.of(11, 0));
        filteredMealsWithExcess.forEach(System.out::println);
        System.out.println();
        System.out.println(mealController.getBetween(null, null, null, null));
    }*/
}
