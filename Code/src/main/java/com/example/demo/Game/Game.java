package com.example.demo.Game;

import com.example.demo.Team.Team;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity(name = "Game")
public class Game {

    @Id
    @SequenceGenerator(
            name = "game_sequence",
            sequenceName = "game_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "game_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private long id;

    @Column(
            name = "location",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String location;

    @Column(
            name = "date",
            nullable = false
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate gameDate;

    @Column(
            name = "time",
            nullable = false
    )
    private String time;

    @ManyToOne(targetEntity = Team.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "team1_id", nullable = false)
    private Team team1;

    @ManyToOne(targetEntity = Team.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "team2_id", nullable = false)
    private Team team2;

    @Column(name = "finished")
    private Boolean finished;

    @Transient
    private String name;

    public Game() {
    }

    public Game(String location, LocalDate gameDate, Team team1, Team team2, String time) {
        this.location = location;
        this.gameDate = gameDate;
        this.time = time;
        this.team1 = team1;
        this.team2 = team2;
        this.finished = false;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        Team team1 = this.getTeam1();
        Team team2 = this.getTeam2();
        String name1 = team1.getName();
        String name2 = team2.getName();
        return String.format("%1$s vs %2$s", name1, name2);
    }

}
