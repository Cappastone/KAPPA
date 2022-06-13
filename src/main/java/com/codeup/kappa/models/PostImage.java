package com.codeup.kappa.models;

import javax.persistence.*;

@Entity
@Table(name = "post_images")
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String url;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public PostImage(){}

    public PostImage(String title, String url, Post post) {
        this.title = title;
        this.url = url;
        this.post = post;
    }

    public PostImage(String title, Post post) {
        this.title = title;
        this.post = post;
    }

    public PostImage(String url) {
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
