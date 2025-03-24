package org.example.minitournamentbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamDTO {
    private int id;
    private String name;
    private int played;
    private int wins;
    private int draws;
    private int losses;
    private int goalDifference;
    private int goalsScored;
    private int goalsConceded;
    private int points;

}
