package com.example.demo.Team;

import com.example.demo.AppUser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private AppUserService appUserService;


    @GetMapping("/teams")
    public String players(Model model) {
        model.addAttribute("teams", this.teamService.getAllTeams());
        model.addAttribute("role", appUserService.getRole());

        return "teams";
    }

    @GetMapping("/team_details")
    public String team_details(Model model, @RequestParam(value="id") Long id) {
        Optional <Team> op = this.teamService.getTeam(id);
        model.addAttribute("team", op.get());
        model.addAttribute("role", appUserService.getRole());

        return "team_page";
    }

    @GetMapping("/new_team")
    public String new_team(Model m) {
        m.addAttribute("team", new Team());
        m.addAttribute("role", appUserService.getRole());
        return "create_team";
    }

    @PostMapping("/save_team")
    public String saveTeam(@ModelAttribute Team team) {
        this.teamService.addTeam(team);
        return "redirect:/teams";
    }

    public String getEditTeam(Long id, String formName, Model m) {
        Optional<Team> op = this.teamService.getTeam(id);
        if (op.isPresent()) {
            m.addAttribute("team", op.get());
            return formName;
        }
        return "redirect:/teams";
    }

    @GetMapping("/edit_team")
    public String editTeam(@RequestParam(name="id", required = true) Long id, Model m) {
        m.addAttribute("role", appUserService.getRole());
        return getEditTeam(id, "create_team", m);
    }

}
