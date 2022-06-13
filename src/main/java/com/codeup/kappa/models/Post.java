package com.codeup.kappa.models;

import jdk.jfr.Timestamp;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT")
    private String body;

//    @Column(columnDefinition = "timestamp default current_timestamp")

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<PostImage> postImages = new ArrayList<>();

    @OneToMany(mappedBy = "post")
   private List<Comment> comments;

//    this is if we just want to track likes =>
    @ManyToMany(mappedBy="likedPosts")
    private List<User> usersThatLiked;

    public Post() {}

    public Post(long id, String body, User user, List<PostImage> postImages, List<Comment> comments, List<User> usersThatLiked) {
        this.id = id;
        this.body = body;
        this.user = user;
        this.postImages = postImages;
        this.comments = comments;
        this.usersThatLiked = usersThatLiked;
    }

    public Post(String body, User user) {
        this.body = body;
        this.user = user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public List<PostImage> getPostImages() {
        return postImages;
    }

    public void setPostImages(List<PostImage> postImages) {
        this.postImages = postImages;
    }


    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<User> getUsersThatLiked() {
        return usersThatLiked;
    }

    public void setUsersThatLiked(List<User> usersThatLiked) {
        this.usersThatLiked = usersThatLiked;
    }


}
