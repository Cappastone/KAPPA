package com.codeup.kappa.models;

import javax.persistence.*;
import java.util.List;

@Entity
//@Table(name = "games")
@Table(name = "games", uniqueConstraints = @UniqueConstraint(columnNames = {"GamesApiId"}))
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    public Game(long gamesApiId, String title, String description, String imageUrl, String platforms, String genre, String developer) {
        GamesApiId = gamesApiId;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.platforms = platforms;
        this.genre = genre;
        this.developer = developer;
    }

    public Game(long gamesApiId, String title, String description, String imageUrl, String platforms, String ageRating, String genre, String developer) {
        GamesApiId = gamesApiId;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.platforms = platforms;
        this.ageRating = ageRating;
        this.genre = genre;
        this.developer = developer;
    }

    public Game(String title, String description, String imageUrl, String platforms) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.platforms = platforms;
    }

    public Game(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String platforms) {
        this.platforms = platforms;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

