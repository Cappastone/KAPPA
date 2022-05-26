package com.codeup.kappa.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isBlocked;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isAdmin;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column(length = 1000)
    private String profilePicture;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @OneToOne(cascade = CascadeType.ALL)
    private PlatformLinks links;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<FavoriteGames> favoriteGames = new ArrayList<>();

//    @OneToOne(cascade = CascadeType.ALL)
//    private UserDetails userDetails;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Posts> posts;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<Followers> followers = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "follower_id")
//    private Followers follower;

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(
//            name = "user_followers",
//            joinColumns = {@JoinColumn(name = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "follower_id")}
//    )
//    private List<Followers> followers = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_followers",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "follower_id")}
    )
    private List<User> followers = new ArrayList<>();

    public User(){}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

}
