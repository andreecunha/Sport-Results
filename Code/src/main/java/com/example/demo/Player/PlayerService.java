package com.example.demo.Player;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public void addPlayer(Player player){
        playerRepository.save(player);
    }

    public Optional<Player> getPlayer(Long id) {
        return playerRepository.findById(id);
    }

}
