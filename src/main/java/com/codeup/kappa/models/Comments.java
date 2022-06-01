package com.codeup.kappa.models;

import javax.persistence.*;
import java.util.Date;


//this class/entity is needed to add the comment row to the join table =>
@Entity
@Table(name = "post_comments")
public class Comments {

//    @EmbeddedId
//    CommentsKey id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column
    private Date timestamp;

    @ManyToOne
//    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
//    @MapsId("postId")
    @JoinColumn(name = "post_id")
    private Posts post;

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
