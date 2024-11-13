package com.example.demo.Team;

import com.example.demo.Player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query(value = "SELECT id FROM player p WHERE p.team = :team", nativeQuery = true)
    public Optional <List<Integer>> getPlayersTeam(@Param("team") Long teamId);

}
