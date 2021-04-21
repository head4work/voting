package ru.homeproject.voting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.homeproject.voting.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v WHERE v.created=:created and v.user.id=:id")
    Vote findById(@Param("created") LocalDate created, @Param("id") int userId);

    @Query("SELECT v FROM Vote v WHERE v.created=:created and v.restaurant.id=:id")
    List<Vote> findAll(@Param("created") LocalDate date, @Param("id") int restId);
}
