package ru.homeproject.voting.util;

import ru.homeproject.voting.model.Dish;
import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.repository.memory.InMemoryRestaurant;
import ru.homeproject.voting.repository.memory.InMemoryUser;
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
        repository.getAllSorted(1).forEach(System.out::println);
        vote(restaurant2, 1);
        vote(restaurant2, 2);
        vote(restaurant1, 2);

        getSortedByVotes(repository.getAllSorted(1)).forEach(System.out::println);

    }

    private static List<RestaurantTo> getSortedByVotes(List<Restaurant> list) {
        return list.stream().map(MainPlay::createTo)
                .sorted(Comparator.comparing(RestaurantTo::getVotes).reversed())
                .collect(Collectors.toList());

    }

    private static void vote(Restaurant r, int userId) {
        if (user.hasVote(userId, LocalDate.now())) {
            Set<Integer> votedUsers = r.getVotedUsers();
            votedUsers.add(userId);
            r.setVotedUsers(votedUsers);
            user.banVote(userId, LocalDate.now());
        } else {
            System.out.println("u have voted already");
        }
    }

    private void revokeVote(Restaurant r, int userId) {
        if (!user.hasVote(userId, LocalDate.now())) {
            if (LocalDateTime.now().getHour() < 11) {

            }else {
                System.out.println("too late to change mind");
            }
        } else {
            System.out.println("u didnt vote yet");
        }

    }

    private void changeVote(Restaurant rToDeleteVote, Restaurant rToAssignVote, int userId) {
        Set<Integer> votes = rToDeleteVote.getVotedUsers();
        Set<Integer> votes1 = rToAssignVote.getVotedUsers();
        if (dateCheck(rToDeleteVote)) {
            if (votes.contains(userId) && votes1.contains(userId)) {
                votes.remove(userId);
            }
        }
        throw new IllegalStateException("Its too late to change your mind");
    }

    private boolean dateCheck(Restaurant r) {
        return r.getCreated().getDayOfYear() == LocalDateTime.now().getDayOfYear();
    }

    private static RestaurantTo createTo(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getId(), restaurant.getCreated(), restaurant.getMenu(), countVotes(restaurant));
    }

    private static Integer countVotes(Restaurant restaurant) {
        return restaurant.getVotedUsers().size();
    }

}
