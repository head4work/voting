package ru.homeproject.voting.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.model.User;
import ru.homeproject.voting.model.Vote;
import ru.homeproject.voting.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Repository
public class DataJpaVoteRepository implements VoteRepository {
    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudUserRepository crudUserRepository;
    private final CrudVoteRepository crudVoteRepository;

    public DataJpaVoteRepository(CrudRestaurantRepository crudRestaurantRepository,
                                 CrudUserRepository crudUserRepository, CrudVoteRepository crudVoteRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudUserRepository = crudUserRepository;
        this.crudVoteRepository = crudVoteRepository;
    }

    private Vote getUserVote(int userId) {
        return crudVoteRepository.findById(LocalDate.now(), userId);
    }

    @Override
    public void saveVote(int restId, int userId) {
        Restaurant restaurant = crudRestaurantRepository.getOne(restId);
        User user = crudUserRepository.getOne(userId);
        Vote userVote = getUserVote(userId);
        if (userVote == null) {
            Vote vote = new Vote();
            vote.setUser(user);
            vote.setRestaurant(restaurant);
            vote.setCreated(LocalDate.now());
            crudVoteRepository.save(vote);
        } else if (LocalDateTime.now().getHour() < 11) {
            crudVoteRepository.save(userVote);
        }

    }

    @Override
    public Integer getVotes(LocalDate date, int restId) {
        return crudVoteRepository.findAll(date, restId).size();
    }

    @Override
    public Map<Integer, Long> getAllRestaurantVotes(LocalDate date) {
        return null;
    }
}
