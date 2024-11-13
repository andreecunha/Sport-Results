package com.example.demo.Event;

import com.example.demo.AppUser.AppUserService;
import com.example.demo.Game.Game;
import com.example.demo.Game.GameService;
import com.example.demo.Player.Player;
import com.example.demo.Player.PlayerService;
import com.example.demo.Team.Team;
import com.example.demo.Team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import java.awt.desktop.AppForegroundListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class EventController {

    @Autowired
    private GameService gameService;

    @Autowired
    private EventService eventService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamRepository teamRepository;

    @PostMapping("/save_event")
    public String saveTeam(@ModelAttribute Event event) {
        this.eventService.addEvent(event);
        return "redirect:/games";
    }

    @GetMapping("new_game_event")
    public String new_event(@RequestParam(value = "game_id") Long gameId, Model m) {
        Event event = new Event();
        Optional<Game> op = gameService.getGame(gameId);

        if (op.isPresent()) {
            Game game = op.get();
            event.setGame(game);
            m.addAttribute("event", event);
            m.addAttribute("game", game);
            m.addAttribute("role", appUserService.getRole());
            return "create_event";
        } else
            return "games";
    }

    @GetMapping("new_player_event")
    public String new_player_event(@RequestParam(value = "game_id") Long gameId, Model m) {
        Event event = new Event();
        Optional<Game> op = gameService.getGame(gameId);
        if (op.isPresent()) {

            Game game = op.get();
            event.setGame(game);

            Team team1 = game.getTeam1();
            Team team2 = game.getTeam2();

            List<Player> player = new ArrayList<Player>();
            Optional<List<Integer>> players = teamRepository.getPlayersTeam(team1.getId());
            Optional<List<Integer>> players2 = teamRepository.getPlayersTeam(team2.getId());
            List<Integer> p2 = players2.get();
            List<Integer> p = players.get();
            p2.addAll(p);
            ArrayList<Integer> p3 = (ArrayList<Integer>) p2;

            for (int i = 0; i < p3.size(); i++) {
                Long id = Long.valueOf(p3.get(i));
                Optional<Player> pp = playerService.getPlayer(id);
                player.add(pp.get());
            }


            m.addAttribute("game", game);
            m.addAttribute("role", appUserService.getRole());
            m.addAttribute("players", player);
            m.addAttribute("team1", team1);
            m.addAttribute("team2", team2);
            m.addAttribute("event", event);
            return "create_player_event";
        } else
            return "games";
    }

    @PostMapping("new_start")
    public String new_start(@RequestParam(value = "game_id") Long gameId) {
        Optional<Game> op = gameService.getGame(gameId);
        if (op.isPresent()) {
            Game game = op.get();
            Event start = new Event(game);
            this.eventService.addEvent(start);
            return String.format("redirect:/game_details?id=%s", gameId);
        }
        return "games";
    }


}
