package com.example.demo.Team;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Optional<Team> getTeam(Long id) {
        return teamRepository.findById(id);
    }

    public void addTeam(Team team) {
        teamRepository.save(team);
    }

}
