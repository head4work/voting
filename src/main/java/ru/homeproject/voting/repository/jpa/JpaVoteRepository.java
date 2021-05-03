package ru.homeproject.voting.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.homeproject.voting.model.Restaurant;
import ru.homeproject.voting.model.User;
import ru.homeproject.voting.model.Vote;
import ru.homeproject.voting.repository.VoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
@Transactional(readOnly = true)
public class JpaVoteRepository implements VoteRepository {
    public static final int TIME_UNTIL_VOTE_CAN_BE_CHANGED = 11;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Vote saveVote(int restId, int userId) {
        Restaurant restaurant = em.getReference(Restaurant.class, restId);
        User user = em.getReference(User.class, userId);

        Vote usersVote = getUsersVote(userId);
        if (usersVote == null) {
            Vote vote = new Vote();
            vote.setRestaurant(restaurant);
            vote.setUser(user);
            vote.setCreated(LocalDate.now());
            em.persist(vote);
            return vote;
        } else if (LocalDateTime.now().getHour() < TIME_UNTIL_VOTE_CAN_BE_CHANGED) {
            em.merge(usersVote);
            return usersVote;
        } else {
            return null;
        }
    }

    private List<Vote> getVotesByDate(LocalDate date) {
        return em.createNamedQuery(Vote.BY_DATE, Vote.class)
                .setParameter("created", date)
                .getResultList();
    }

    private Vote getUsersVote(int userId) {
        return getVotesByDate(LocalDate.now()).stream().filter(vote -> vote.getUser()
                .getId().equals(userId)).findFirst().orElse(null);
    }

    @Override
    public Integer getVotes(LocalDate date, int r) {
        return em.createNamedQuery(Vote.BY_RESTAURANT, Vote.class)
                .setParameter("created", date)
                .setParameter("restaurant", r)
                .getResultList().size();
    }

    @Override
    public Map<Integer, Long> getAllRestaurantVotes(LocalDate date) {
        return null;
    }
}
