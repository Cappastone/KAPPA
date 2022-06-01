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

    @Column(length = 50)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @Column
    private String platforms;

    @Column
    private String ageRating;

    @Column
    private String genre;

    @Column
    private String developer;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "favoriteGames")
    private List<User> users;

    public Games(){}

    public Games(String title, String body, String imageUrl, String platforms) {
        this.title = title;
        this.body = body;
        this.imageUrl = imageUrl;
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
        return imageUrl;
    }

    public void setImage(String image) {
        this.imageUrl = image;
    }

}
