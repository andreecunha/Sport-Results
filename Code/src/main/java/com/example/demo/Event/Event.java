package com.example.demo.Event;

import com.example.demo.Game.Game;
import com.example.demo.Player.Player;
import com.example.demo.Team.Team;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity(name = "event")
public class Event {

    @Id
    @SequenceGenerator(
            name = "event_sequence",
            sequenceName = "event_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "event_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(targetEntity = Player.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id")
    private Player player = null;

    @ManyToOne(targetEntity = Game.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne(targetEntity = Team.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(
            name = "time",
            nullable = false
    )
    private int gameTime;

    public Event() {
    }

    public Event(String name, int gameTime, Game game) {
        this.name = name;
        this.gameTime = gameTime;
        this.game = game;
    }

    public Event(String name, int gameTime, Game game, Player player, Team team) {
        this.name = name;
        this.gameTime = gameTime;
        this.game = game;
        this.player = player;
        this.team = team;
    }

    public Event(Game game) {
        this.name = "Start";
        this.gameTime = 0;
        this.game = game;
    }

    public long getId() {
        return id;
    }


}
