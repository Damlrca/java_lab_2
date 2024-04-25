package org.example.java_lab;

import javax.persistence.*;

@Entity
@Table
public class Lab3_TablePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column
    String playerName;

    @Column
    int wins;

    public Lab3_TablePlayer() {
    }

    public Lab3_TablePlayer(String playerName, int wins) {
        this.playerName = playerName;
        this.wins = wins;
    }
}
