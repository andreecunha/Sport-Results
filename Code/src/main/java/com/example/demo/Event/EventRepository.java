package com.example.demo.Event;

import com.example.demo.Player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "SELECT count(id) FROM (SELECT * FROM event ev WHERE ev.name='Goal') AS x GROUP BY x.game_id, x.team_id HAVING x.game_id = :game AND team_id = :team", nativeQuery = true)
    public Optional <Integer> getTeamGoals(@Param("game") Long gameId, @Param("team") Long teamId);

    @Query(value = "SELECT count(id) FROM (SELECT * FROM event ev WHERE ev.name='Goal')AS x GROUP BY player_id HAVING player_id = :player", nativeQuery = true)
    public Optional <Integer> getPlayerGoals(@Param("player") Long playerId);

    @Query(value = "SELECT * FROM event ev WHERE ev.game_id = :game ORDER BY ev.time", nativeQuery = true)
    public Optional <List<Event>> getEvents(@Param("game") Long teamId);

    @Query(value = "SELECT * FROM event ev WHERE ev.game_id = :game AND ev.name = 'Start'", nativeQuery = true)
    public Optional <List<Event>> getStart(@Param("game") Long gameId);

    @Query(value = "select player_id from(select player_id, count(id) from(select * from event ev where ev.name='Goal')as x group by x.player_id order by count(id) DESC) as y", nativeQuery = true)
    public  List <Integer> getScorers();

}
