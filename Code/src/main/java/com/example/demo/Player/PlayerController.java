package com.example.demo.Player;

import com.example.demo.AppUser.AppUserService;
import com.example.demo.Event.EventRepository;
import com.example.demo.Team.Team;
import com.example.demo.Team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PlayerController {

    private final PlayerService playerService;
    private final TeamService teamService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    public PlayerController(PlayerService playerService, TeamService teamService, AppUserService appUserService) {
        this.playerService = playerService;
        this.teamService = teamService;
        this.appUserService = appUserService;
    }

    @GetMapping("/players")
    public String players(Model model) {
        model.addAttribute("role", appUserService.getRole());
        model.addAttribute("players", this.playerService.getAllPlayers());
        return "players";
    }

    @GetMapping("/player_details")
    public String player_details(Model model, @RequestParam(value="id") Long id) {
        Optional <Player> op = this.playerService.getPlayer(id);
        if (op.isEmpty()) {
            return "Invalid Id";
        }
        else {
            Player player = op.get();
            Team team = player.getTeam();
            model.addAttribute("player", player);
            model.addAttribute("goals", getPlayerGoals(op.get()));
            model.addAttribute("team", team);
            model.addAttribute("role", appUserService.getRole());
            return "player_page";
        }
    }

    @GetMapping("/new_player")
    public String new_player(Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("role", appUserService.getRole());
        model.addAttribute("allTeams", this.teamService.getAllTeams());
        return "create_player";
    }


    @PostMapping("/save_player")
    public String registerNewPlayer(Player player) {
        this.playerService.addPlayer(player);
        return "redirect:/players";
    }

    public String getEditPlayer(Long id, String formName, Model m) {
        Optional<Player> op = this.playerService.getPlayer(id);
        if (op.isPresent()) {
            m.addAttribute("player", op.get());
            return formName;
        }
        return "redirect:/players";
    }

    @GetMapping("/edit_player")
    public String editTeam(@RequestParam(name="id", required = true) Long id, Model m) {
        m.addAttribute("role", appUserService.getRole());
        m.addAttribute("allTeams", teamService.getAllTeams());
        return getEditPlayer(id, "create_player", m);
    }

    public int getPlayerGoals(Player player) {
        int numGoals;
        Optional <Integer> goals = eventRepository.getPlayerGoals(player.getId());
        if (goals.isEmpty()) {
            numGoals = 0;
        }
        else {
            numGoals = goals.get();
        }
        return numGoals;
    }

    @GetMapping("/stats")
    public String home(Model m) {
        List <Integer> scorers = eventRepository.getScorers();
        List <Player> players = new ArrayList<Player>();
        ArrayList <Integer> p3 = (ArrayList<Integer>) scorers;

        for (int i = 0; i < p3.size(); i++ ) {
            Long id = Long.valueOf(p3.get(i));
            Optional <Player> pp = playerService.getPlayer(id);
            players.add(pp.get());
        }
        m.addAttribute("players", players);
        return "stats";
    }

}


