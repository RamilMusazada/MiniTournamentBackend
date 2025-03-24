package org.example.minitournamentbackend.mapper;

import org.example.minitournamentbackend.dto.TeamDTO;
import org.example.minitournamentbackend.entity.Team;

import java.util.List;
import java.util.stream.Collectors;

public class TeamMapper {
    public static List<TeamDTO> toDTOList(List<Team> teams) {
        return teams.stream().map(TeamMapper::toDTO).collect(Collectors.toList());
    }
    public static TeamDTO toDTO(Team team) {
        return new TeamDTO(
                team.getId(),
                team.getName(),
                team.getPlayed(),
                team.getWins(),
                team.getDraws(),
                team.getLosses(),
                team.getGoalDifference(),
                team.getGoalsScored(),
                team.getGoalsConceded(),
                team.getPoints()
        );
    }

    public static Team toEntity(TeamDTO dto) {
        return new Team(
                dto.getId(),
                dto.getName(),
                dto.getPlayed(),
                dto.getWins(),
                dto.getDraws(),
                dto.getLosses(),
                dto.getGoalDifference(),
                dto.getGoalsScored(),
                dto.getGoalsConceded(),
                "",
                dto.getPoints()
        );
    }
    }

