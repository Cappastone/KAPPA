package com.codeup.kappa.models;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.VARCHAR;

@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(length = 1000)
    private String profilePicture;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @OneToOne(cascade = CascadeType.ALL)
    private PlatformLinks links;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user_details")
    private List<FavoriteGames> favoriteGames = new ArrayList<>();

    public UserDetails(){}

    public UserDetails(String firstName, String lastName, String profilePicture, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.bio = bio;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public PlatformLinks getLinks() {
        return links;
    }

    public void setLinks(PlatformLinks links) {
        this.links = links;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<FavoriteGames> getFavoriteGames() {
        return favoriteGames;
    }

    public void setFavoriteGames(List<FavoriteGames> favoriteGames) {
        this.favoriteGames = favoriteGames;
    }


}
