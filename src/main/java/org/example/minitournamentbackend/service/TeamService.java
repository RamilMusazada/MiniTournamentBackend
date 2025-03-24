package org.example.minitournamentbackend.service;
import org.example.minitournamentbackend.dao.TeamDAO;
import org.example.minitournamentbackend.dto.TeamDTO;
import org.example.minitournamentbackend.entity.Team;
import org.example.minitournamentbackend.mapper.TeamMapper;

import java.util.List;

public class TeamService {
    private final TeamDAO teamDAO;
    private TeamDAO teamDao;

    public TeamService() {
        this.teamDAO = new TeamDAO();
    }

    // 🔹 1. Komanda əlavə et
    public void addTeam(TeamDTO teamDTO) {
        Team team = TeamMapper.toEntity(teamDTO);
        teamDAO.addTeam(team);
    }

    // 🔹 2. Bütün komandaları gətir (DTO qaytarırıq)
    public List<TeamDTO> getAllTeams() {
        return teamDAO.getAllTeams();
    }

    public void addMatchResult(String team1Name, int team1Goals, String team2Name, int team2Goals) {
        // Update first team's stats
        Team team1 = teamDAO.getTeamByName(team1Name);
        // Update second team's stats
        Team team2 = teamDAO.getTeamByName(team2Name);

        if (team1 != null && team2 != null) {
            // Update team1 statistics
            updateTeamStats(team1, team1Goals, team2Goals);

            // Update team2 statistics
            updateTeamStats(team2, team2Goals, team1Goals);

            // Save both teams to the database
            teamDAO.updateTeam(team1);
            teamDAO.updateTeam(team2);
        }
    }

    // Helper method to update a team's statistics
    private void updateTeamStats(Team team, int scoredGoals, int concededGoals) {
        team.setPlayed(team.getPlayed() + 1);
        team.setGoalsScored(team.getGoalsScored() + scoredGoals);
        team.setGoalsConceded(team.getGoalsConceded() + concededGoals);
        team.setGoalDifference(team.getGoalsScored() - team.getGoalsConceded());

        if (scoredGoals > concededGoals) {
            team.setWins(team.getWins() + 1);
            team.setPoints(team.getPoints() + 3);
        } else if (scoredGoals == concededGoals) {
            team.setDraws(team.getDraws() + 1);
            team.setPoints(team.getPoints() + 1);
        } else {
            team.setLosses(team.getLosses() + 1);
        }
    }

    // 🔹 4. Qələbə sayına görə komandaları qaytar
    public List<TeamDTO> getTeamsSortedByWins() {
        return teamDAO.getAllTeams()
                .stream()
                .sorted((t1, t2) -> Integer.compare(t2.getWins(), t1.getWins()))
                .toList();
    }

    // 🔹 5. Xal sayına görə komandaları qaytar
    public List<TeamDTO> getTeamsSortedByPoints() {
        return teamDAO.getAllTeams()
                .stream()
                .sorted((t1, t2) -> Integer.compare(t2.getPoints(), t1.getPoints()))
                .toList();
    }
    public void deleteTeam(int id) {
        teamDAO.deleteTeam(id);
    }
}