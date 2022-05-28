package com.codeup.kappa.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


//this class/entity is needed to add the comment row to the join table =>
@Entity
@Table(name = "LikesAndComments")
public class LikesAndComments {

    @EmbeddedId
    LikesAndCommentsKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name = "post_id")
    Posts post;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isLiked;

    @Column(columnDefinition = "TEXT")
    private String comment;

    public LikesAndComments(){}

    public LikesAndComments(boolean isLiked, String comment) {
        this.isLiked = isLiked;
        this.comment = comment;
    }

    public LikesAndComments(LikesAndCommentsKey id, User user, Posts post, boolean isLiked, String comment) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.isLiked = isLiked;
        this.comment = comment;
    }

    public LikesAndCommentsKey getId() {
        return id;
    }

    public void setId(LikesAndCommentsKey id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Posts getPost() {
        return post;
    }

    public void setPost(Posts post) {
        this.post = post;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
