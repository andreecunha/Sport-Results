package com.example.demo.Game;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Optional<Game> getGame(Long id) {
        return gameRepository.findById(id);
    }

    public void addNewGame(Game game){
        gameRepository.save(game);
    }

    public List <Long> numGames() {

        List <Long> data = gameRepository.numGames1();
        List <Long> data2 = gameRepository.numGames2();
        return data;
    }

}
