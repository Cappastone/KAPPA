package com.codeup.kappa.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "favorite_games")
public class FavoriteGames {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long apiId;

    @Column(nullable = false)
    private String title;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    @ManyToMany(mappedBy="favoriteGames")
    private List<User> users;

    public FavoriteGames(){}

    public FavoriteGames(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public FavoriteGames(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
