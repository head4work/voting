package ru.homeproject.voting.util;

import ru.homeproject.voting.model.Dish;
import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.repository.memory.InMemoryRestaurant;
import ru.homeproject.voting.repository.memory.InMemoryUser;
import ru.homeproject.voting.repository.memory.InMemoryVote;
import ru.homeproject.voting.to.RestaurantTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MainPlay {
    private static final InMemoryRestaurant repository = new InMemoryRestaurant();
    private static final InMemoryUser user = new InMemoryUser();
    private static final InMemoryVote vote = new InMemoryVote();

    private static final Restaurant restaurant1 = new Restaurant(null, "McDonalds", LocalDateTime.of(2020, 3, 1, 2, 3, 0),
            new Dish(null, "soup", 100), new Dish(null, "rice", 50));
    private static final Restaurant restaurant2 = new Restaurant(null, "KFC", LocalDateTime.of(2020, 4, 1, 2, 3, 0),
            new Dish(null, "soup", 100), new Dish(null, "rice", 50));
    private static final Restaurant restaurant3 = new Restaurant(null, "BurgerKing", LocalDateTime.of(2020, 5, 1, 2, 3, 0),
            new Dish(null, "soup", 100), new Dish(null, "rice", 50));

    public static void main(String[] args) {
        repository.save(restaurant1, 1);
        repository.save(restaurant2, 1);
        repository.save(restaurant3, 1);

        vote.saveVote(restaurant1, 1);
        getSortedByVotesNew(repository.getAllSorted(1)).forEach(System.out::println);
        vote.saveVote(restaurant2,1);
        vote.saveVote(restaurant1,2);
        vote.saveVote(restaurant3,3);
        getSortedByVotesNew(repository.getAllSorted(1)).forEach(System.out::println);

        /*repository.getAllSorted(1).forEach(System.out::println);
        vote(restaurant2, 1);
        vote(restaurant2, 2);
        vote(restaurant1, 2);

        getSortedByVotes(repository.getAllSorted(1)).forEach(System.out::println);

        revokeVote(2);
        vote(restaurant1,2);

        getSortedByVotes(repository.getAllSorted(1)).forEach(System.out::println);*/

    }

 /*   private static List<RestaurantTo> getSortedByVotes(List<Restaurant> list) {
        return list.stream().map(MainPlay::createTo)
                .sorted(Comparator.comparing(RestaurantTo::getVotes).reversed())
                .collect(Collectors.toList());

    }*/

    private static List<RestaurantTo> getSortedByVotesNew(List<Restaurant> list) {
        return list.stream().map(MainPlay::castTo)
                .sorted(Comparator.comparing(RestaurantTo::getVotes).reversed())
                .collect(Collectors.toList());

    }

 /*   private static void vote(Restaurant r, int userId) {
        if (user.hasVote(userId, LocalDate.now())) {
            Set<Integer> votedUsers = r.getVotedUsers();
            votedUsers.add(userId);
            r.setVotedUsers(votedUsers);
            user.banVote(r, userId, LocalDate.now());
        } else {
            System.out.println("u have voted already");
        }
    }
*/
 /*   private static void revokeVote(int userId) {
        if (!user.hasVote(userId, LocalDate.now())) {
            if (LocalDateTime.now().getHour() < 20) {
                Integer revokedRestaurantId = user.getRevokedRestaurantId(userId, LocalDate.now());
                user.resetVote(userId, LocalDate.now());
                removeVote(userId, revokedRestaurantId);
            } else {
                System.out.println("too late to change mind");
            }
        } else {
            System.out.println("u didnt vote yet");
        }

    }*/

   /* private static void removeVote(int userId, Integer revokedRestaurantId) {
        Restaurant r = repository.get(revokedRestaurantId, userId);
        Set<Integer> votedUsers = r.getVotedUsers();
        votedUsers.remove(userId);
        r.setVotedUsers(votedUsers);
    }


    private boolean dateCheck(Restaurant r) {
        return r.getCreated().getDayOfYear() == LocalDateTime.now().getDayOfYear();
    }*/

   /* private static RestaurantTo createTo(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getName(), restaurant.getId(), restaurant.getCreated(), restaurant.getMenu(), countVotes(restaurant));
    }
*/
    private static RestaurantTo castTo(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getName(), restaurant.getId(), restaurant.getCreated(), restaurant.getMenu(),
                countVotesNew(restaurant));
    }

    private static Integer countVotesNew(Restaurant restaurant) {
        long votes = vote.getAllRestaurantVotes(LocalDate.now()).get(restaurant.getId()) == null
                ? 0 : vote.getAllRestaurantVotes(LocalDate.now()).get(restaurant.getId());
        return (int) votes;
    }

/*
    private static Integer countVotes(Restaurant restaurant) {
        return restaurant.getVotedUsers().size();
    }
*/

}
