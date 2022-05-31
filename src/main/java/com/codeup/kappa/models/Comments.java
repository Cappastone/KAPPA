package com.codeup.kappa.models;

import javax.persistence.*;


//this class/entity is needed to add the comment row to the join table =>
@Entity
@Table(name = "Comments")
public class Comments {

    @EmbeddedId
    CommentsKey id;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name = "post_id")
    Posts post;

    public Comments(){}

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

}
