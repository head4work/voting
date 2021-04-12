package ru.homeproject.voting.util;

import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.repository.memory.InMemoryRestaurant;
import ru.homeproject.voting.repository.memory.InMemoryVote;
import ru.homeproject.voting.to.RestaurantTo;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MainPlay {
    private static final InMemoryRestaurant repository = new InMemoryRestaurant();
    private static final InMemoryVote vote = new InMemoryVote();

   /* private static final Restaurant restaurant1 = new Restaurant(null, "McDonalds", LocalDateTime.of(2020, 3, 1, 2, 3, 0),
            new Dish(null, "soup", 100), new Dish(null, "rice", 50));
    private static final Restaurant restaurant2 = new Restaurant(null, "KFC", LocalDateTime.of(2020, 4, 1, 2, 3, 0),
            new Dish(null, "soup", 100), new Dish(null, "rice", 50));
    private static final Restaurant restaurant3 = new Restaurant(null, "BurgerKing", LocalDateTime.of(2020, 5, 1, 2, 3, 0),
            new Dish(null, "soup", 100), new Dish(null, "rice", 50));*/

    public static void main(String[] args) {
       /* repository.save(restaurant1);
        repository.save(restaurant2);
        repository.save(restaurant3);

        vote.saveVote(restaurant1, 1);
        getSortedByVotesNew(repository.getAllSorted()).forEach(System.out::println);
        vote.saveVote(restaurant3, 2);
        vote.saveVote(restaurant2, 1);
        vote.saveVote(restaurant3, 3);*/
        getSortedByVotesNew(repository.getAllSorted()).forEach(System.out::println);

    }


    private static List<RestaurantTo> getSortedByVotesNew(List<Restaurant> list) {
        return list.stream().map(MainPlay::castTo)
                .sorted(Comparator.comparing(RestaurantTo::getVotes).reversed())
                .collect(Collectors.toList());

    }

    private static RestaurantTo castTo(Restaurant r) {
        return new RestaurantTo(r.getName(), r.getId(), r.getCreated(), r.getMenu(),
                countVotesNew(r));
    }

    private static Integer countVotesNew(Restaurant r) {
        long votes = vote.getAllRestaurantVotes(LocalDate.now()).get(r.getId()) == null
                ? 0 : vote.getAllRestaurantVotes(LocalDate.now()).get(r.getId());
        return (int) votes;
    }

}
