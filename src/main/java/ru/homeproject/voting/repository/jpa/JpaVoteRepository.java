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

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void saveVote(Restaurant r, int userId) {
        Restaurant restaurant = em.getReference(Restaurant.class, r.id());
        User user = em.getReference(User.class, userId);

        Vote usersVote = usersVote(userId);
        if (usersVote == null) {
            Vote vote = new Vote();
            vote.setRestaurant(restaurant);
            vote.setUser(user);
            vote.setCreated(LocalDate.now());
            em.persist(vote);
        } else if (LocalDateTime.now().getHour() < 11) {
            em.merge(usersVote);
        } else {
            System.out.println("You have voted already and it's too late to change mind");
        }
    }

    private List<Vote> getVotesByDate(LocalDate date) {
        return em.createNamedQuery(Vote.BY_DATE, Vote.class)
                .setParameter("created", date)
                .getResultList();
    }

    private Vote usersVote(int userId) {
        return getVotesByDate(LocalDate.now()).stream().filter(vote -> vote.getUser().getId().equals(userId)).findFirst().orElse(null);
    }

    @Override
    public Integer getVotes(LocalDate date, Restaurant r) {
        //  getVotesByDate(LocalDate.now()).stream()
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