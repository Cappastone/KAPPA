package com.codeup.kappa.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    @Column()
    private int likes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<PostImages> postImages = new ArrayList<>();

    public Posts() {}

    public Posts(String title, String body, User user, List<PostImages> postImages) {
        this.title = title;
        this.body = body;
        this.user = user;
        this.postImages = postImages;
    }

    public Posts(String title, String body, List<PostImages> postImages) {
        this.title = title;
        this.body = body;
        this.postImages = postImages;
    }

    public Posts(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
