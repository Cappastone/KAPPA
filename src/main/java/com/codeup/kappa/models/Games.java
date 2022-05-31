package com.codeup.kappa.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Games {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private long GamesApiId;

    @Column
    private String title;

    @Column
    private String body;

    @Column
    private String image;

    @Column
    private String platforms;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "favoriteGame")
    private List<FavoriteGames> games;

    public Games(){}

    public Games(String title, String body, String image, String platforms) {
        this.title = title;
        this.body = body;
        this.image = image;
        this.platforms = platforms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getGamesApiId() {
        return GamesApiId;
    }

    public void setGamesApiId(long gamesApiId) {
        GamesApiId = gamesApiId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String platforms) {
        this.platforms = platforms;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<FavoriteGames> getGames() {
        return games;
    }

    public void setGames(List<FavoriteGames> games) {
        this.games = games;
    }
}
