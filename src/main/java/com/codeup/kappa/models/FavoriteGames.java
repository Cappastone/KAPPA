//package com.codeup.kappa.models;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "favorite_games")
//public class FavoriteGames {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
////    @Column
////    private long GamesApiId;
//
////    @Column(nullable = false)
////    private String title;
//
////    @ManyToOne
////    @JoinColumn(name = "user_id")
////    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "game_id")
//    private Games favoriteGame;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
////    @ManyToMany(mappedBy="favoriteGames")
////    private List<User> users;
//
//    public FavoriteGames(){}
//
//    public FavoriteGames(Games favoriteGame) {
//        this.favoriteGame = favoriteGame;
//    }
//
//    public Games getFavoriteGame() {
//        return favoriteGame;
//    }
//
//    public void setFavoriteGame(Games favoriteGame) {
//        this.favoriteGame = favoriteGame;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//}
