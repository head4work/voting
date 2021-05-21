package ru.homeproject.voting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.homeproject.voting.model.Vote;

import java.time.LocalDate;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v WHERE v.created=:created and v.user.id=:id")
    Vote findById(@Param("created") LocalDate created, @Param("id") int userId);

    @Query("SELECT COUNT(*) FROM Vote v WHERE v.created=:created and v.restaurant.id=:id")
    long countByDateAndRestaurant(@Param("created") LocalDate date, @Param("id") int restId);

    @Transactional
    @Modifying
    @Query("UPDATE  Vote v set v.restaurant.id=:id  WHERE v.created=:created and v.user.id=:uid")
    int update(@Param("id") int restId, @Param("created") LocalDate date, @Param("uid") int uId);
}
