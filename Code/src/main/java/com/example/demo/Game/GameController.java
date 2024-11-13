package com.example.demo.Game;

import com.example.demo.AppUser.AppUserService;
import com.example.demo.Event.Event;
import com.example.demo.Event.EventRepository;
import com.example.demo.Team.Team;
import com.example.demo.Team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class GameController {

    private final GameService gameService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private GameController(GameService playerService) {
        this.gameService = playerService;
    }

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/games")
    public String games(Model model) {
        model.addAttribute("role", appUserService.getRole());
        model.addAttribute("games", this.gameService.getAllGames());
        return "games";
    }

    @Autowired
    private TeamService teamService;

    @GetMapping("/game_details")
    public String game_details(Model model, @RequestParam(value="id") Long id) {
        Optional<Game> op = this.gameService.getGame(id);
        if (op.isPresent()) {
            Game game = op.get();
            Team t1 = game.getTeam1();
            Team t2 = game.getTeam2();
            int g1 = getGoals(game, t1);
            int g2 = getGoals(game, t2);
            int started = getStart(game);

            model.addAttribute("game", game);
            model.addAttribute("goals1", g1);
            model.addAttribute("goals2", g2);
            model.addAttribute("team1", t1);
            model.addAttribute("team2", t2);
            model.addAttribute("started", started);
            model.addAttribute("role", appUserService.getRole());

            Optional <List<Event>> events = eventRepository.getEvents(game.getId());

            if (events.isEmpty()) {
            }
            else {
                model.addAttribute("events", events.get());
            }
            return "game_page";
        } else
            return "/";
        }

    @GetMapping("/new_game")
    public String new_game(Model model) {
        model.addAttribute("game", new Game());
        model.addAttribute("role", appUserService.getRole());
        model.addAttribute("allTeams", this.teamService.getAllTeams());
        return "create_game";
    }


    @PostMapping("/save_game")
    public String registerNewGame(Game game) {
        this.gameService.addNewGame(game);
        return "redirect:/games";
    }

    @PostMapping("/end_game")
    public String end_game(@RequestParam(value = "game_id") Long gameId) {
        gameRepository.updateGame(gameId);
        return String.format("redirect:/game_details?id=%s", gameId);
    }


    @GetMapping("/edit_game")
    public String editTeam(@RequestParam(name="id", required = true) Long id, Model model) {
        model.addAttribute("role", appUserService.getRole());
        model.addAttribute("allTeams", this.teamService.getAllTeams());
        return getEditGame(id, "create_game", model);
    }

    public String getEditGame(Long id, String formName, Model m) {
        Optional<Game> op = this.gameService.getGame(id);
        if (op.isPresent()) {
            m.addAttribute("game", op.get());
            return formName;
        }
        return String.format("redirect:/game_details?id=%s", id);
    }

    public int getStart(Game game) {
        int started;
        Optional <List<Event>> start = eventRepository.getStart(game.getId());
        List <Event> starts = start.get();
        System.out.println(starts);
        if (starts.isEmpty())
            started = 0;
        else
            started = 1;
        return started;
    }

    public int getGoals(Game game, Team team) {
        int numGoals;
        Optional <Integer> goals = eventRepository.getTeamGoals(game.getId(), team.getId());
        if (goals.isEmpty()) {
            numGoals = 0;
        }
        else {
            numGoals = goals.get();
        }
        return numGoals;
    }

}
