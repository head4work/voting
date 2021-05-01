package ru.homeproject.voting.web.restaurant;

import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.repository.RestaurantRepository;
import ru.homeproject.voting.repository.VoteRepository;
import ru.homeproject.voting.to.RestaurantTo;

import java.util.List;

public class RestaurantRestController extends AbstractRestaurantController {
    public RestaurantRestController(RestaurantRepository repository, VoteRepository vote) {
        super(repository, vote);
    }

    @Override
    public Restaurant create(Restaurant r) {
        return super.create(r);
    }

    @Override
    public Restaurant get(int id) {
        return super.get(id);
    }

    @Override
    public void update(Restaurant r, int id) {
        super.update(r, id);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @Override
    public List<RestaurantTo> getAllSortedByVotes() {
        return super.getAllSortedByVotes();
    }

    @Override
    public Integer countVotes(int r) {
        return super.countVotes(r);
    }
}
