package com.codeup.kappa.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(columnDefinition = "TEXT")
    private String body;

    @Column
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<PostImages> postImages = new ArrayList<>();

//    @OneToMany(mappedBy = "post")
//    Set<Comments> comments;

    @OneToMany(mappedBy = "post")
   private List<Comments> comments;

//    this is if we just want to track likes =>
    @ManyToMany(mappedBy="likedPosts")
    private List<User> usersThatLiked;

    public Posts() {}

    public Posts(long id, String body, Date timestamp, User user, List<PostImages> postImages, List<Comments> comments, List<User> usersThatLiked) {
        this.id = id;
        this.body = body;
        this.timestamp = timestamp;
        this.user = user;
        this.postImages = postImages;
        this.comments = comments;
        this.usersThatLiked = usersThatLiked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PostImages> getPostImages() {
        return postImages;
    }

    public void setPostImages(List<PostImages> postImages) {
        this.postImages = postImages;
    }

}
