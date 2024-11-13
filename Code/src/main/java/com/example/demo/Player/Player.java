package com.example.demo.Player;

import com.example.demo.Team.Team;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity(name = "Player")
public class Player {

     @Id
     @SequenceGenerator(
             name = "player_sequence",
             sequenceName = "player_sequence",
             allocationSize = 1
     )
     @GeneratedValue(
             strategy = SEQUENCE,
             generator = "player_sequence"
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
             name = "position",
             nullable = false,
             columnDefinition = "TEXT"
     )
     private String position;

     @Column(
             name = "birth_date",
             nullable = false
     )
     @DateTimeFormat(pattern = "yyyy-MM-dd")
     private LocalDate birthDate;


     @ManyToOne(targetEntity = Team.class, cascade = CascadeType.ALL)
     @JoinColumn(name = "team", nullable = false)
     private Team team;

     @Transient
     private Integer age;

     public Player() {
     }

     public Player(String name, String position, LocalDate birthDate, Team team) {
          this.name = name;
          this.position = position;
          this.birthDate = birthDate;
          this.team = team;
     }

     public Integer getAge() {
          return Period.between(this.birthDate, LocalDate.now()).getYears();
     }

}
