package org.example.minitournamentbackend.controller;

import org.example.minitournamentbackend.dto.TeamDTO;
import org.example.minitournamentbackend.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teams")
@CrossOrigin(origins = "http://localhost:3000")
public class TeamController {
    private final TeamService teamService;

    public TeamController() {
        this.teamService = new TeamService();
    }

    // 🔹 1. Yeni komanda əlavə et (POST)
    @PostMapping("/add")
    public String addTeam(@RequestBody TeamDTO teamDTO) {
        teamService.addTeam(teamDTO);
        return "Team added successfully!";
    }

    // 🔹 2. Bütün komandaları qaytar (GET)
    @GetMapping("/all")
    public List<TeamDTO> getAllTeams() {
        return teamService.getAllTeams();
    }

    // 🔹 3. Oyunun nəticəsini əlavə et (PUT)
    @PutMapping("/update-match-result")
    public String updateMatchResult(
            @RequestParam String team1Name,
            @RequestParam String team2Name,
            @RequestParam int team1Goals,
            @RequestParam int team2Goals) {
        teamService.addMatchResult(team1Name, team1Goals, team2Name, team2Goals);
        return "Match result updated successfully!";
    }

    // 🔹 4. Qələbəyə görə sıralanmış komandalar (GET)
    @GetMapping("/sorted-by-wins")
    public List<TeamDTO> getTeamsByWins() {
        return teamService.getTeamsSortedByWins();
    }

    // 🔹 5. Xal sayına görə sıralanmış komandalar (GET)
    @GetMapping("/sorted-by-points")
    public List<TeamDTO> getTeamsByPoints() {
        return teamService.getTeamsSortedByPoints();
    }
    @DeleteMapping("/delete/{id}")
    public String deleteTeam(@PathVariable int id) {
        teamService.deleteTeam(id);
        return "Team deleted successfully!";
    }
}