package com.example.demo.Game;

import com.example.demo.Event.Event;
import com.example.demo.Team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE game SET finished=true WHERE id = :game", nativeQuery = true)
    public void updateGame(@Param("game") Long gameId);

    @Query(value = "select team1_id from(select * from game where finished) as x group by x.team1_id order by count(team1_id) DESC", nativeQuery = true)
    public List <Long> numGames1();

    @Query(value = "select team2_id from(select * from game where finished) as x group by x.team2_id order by count(team2_id) DESC", nativeQuery = true)
    public List <Long> numGames2();
}
