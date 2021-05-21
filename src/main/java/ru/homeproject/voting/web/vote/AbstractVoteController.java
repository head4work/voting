package ru.homeproject.voting.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.model.User;
import ru.homeproject.voting.model.Vote;
import ru.homeproject.voting.repository.datajpa.CrudRestaurantRepository;
import ru.homeproject.voting.repository.datajpa.CrudUserRepository;
import ru.homeproject.voting.repository.datajpa.CrudVoteRepository;
import ru.homeproject.voting.util.exception.VoteExpiredException;
import ru.homeproject.voting.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class AbstractVoteController {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    public static final int TIME_UNTIL_VOTE_CAN_BE_CHANGED = 11;
    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudUserRepository crudUserRepository;
    private final CrudVoteRepository crudVoteRepository;

    public AbstractVoteController(CrudRestaurantRepository crudRestaurantRepository,
                                  CrudUserRepository crudUserRepository, CrudVoteRepository crudVoteRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudUserRepository = crudUserRepository;
        this.crudVoteRepository = crudVoteRepository;
    }

    public Vote vote(int restId) {
        int userId = SecurityUtil.authUserId();
        return saveVote(restId, userId);
    }

    @Transactional
    public Vote saveVote(int restId, int userId) {
        log.info("vote {}", restId);
        Restaurant restaurant = crudRestaurantRepository.getOne(restId);
        User user = crudUserRepository.getOne(userId);
        Vote userVote = getUserVote(userId);
        if (userVote == null) {
            Vote vote = new Vote();
            vote.setUser(user);
            vote.setRestaurant(restaurant);
            vote.setCreated(LocalDate.now());
            crudVoteRepository.save(vote);
            return vote;
        } else if (LocalDateTime.now().getHour() < TIME_UNTIL_VOTE_CAN_BE_CHANGED &&
                !userVote.getRestaurant().getId().equals(restId)) {
            userVote.setRestaurant(restaurant);
            crudVoteRepository.save(userVote);
            return userVote;
        } else {
            throw new VoteExpiredException();
        }
    }

    private Vote getUserVote(int userId) {
        return crudVoteRepository.findById(LocalDate.now(), userId);
    }

    public Integer getVotes(LocalDate date, int restId) {
      return (int) crudVoteRepository.countByDateAndRestaurant(date, restId);
    }

}
