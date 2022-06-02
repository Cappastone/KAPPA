package com.codeup.kappa.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private long GamesApiId;

    @Column(length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 1000)
    private String imageUrl;

    @Column

    private String platforms; //long string of genres seperated by commas.


    @Column
    private String ageRating;

    @Column(length = 500)

    private String genre; //long string of genres seperated by commas.




    @Column
    private String developer;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "favoriteGames")
    private List<User> users;

    public Game() {
    }

    public Game(String title, String description, String imageUrl, String platforms) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.platforms = platforms;
    }








}

