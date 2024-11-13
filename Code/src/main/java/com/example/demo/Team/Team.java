package com.example.demo.Team;

import com.example.demo.Player.Player;

import java.util.List;
import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Team")
public class Team {

    public static Object Teamservice;
    @Id
    @SequenceGenerator(
            name = "team_sequence",
            sequenceName = "team_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "team_sequence"
    )

    @Column(
            name = "id",
            updatable = false
    )
    private long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "picture",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String picture;

    @OneToMany(targetEntity = Player.class, cascade = CascadeType.ALL)
    private List<Player> players;

    public Team() {
    }

    public Team(String name, String picture) {
        this.name = name;
        this.picture = picture;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


}
