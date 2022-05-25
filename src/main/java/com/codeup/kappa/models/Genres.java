package com.codeup.kappa.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genres")
public class Genres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String genres;

    @ManyToMany(mappedBy = "genres")
    private List<Posts> posts;

    public Genres(){}

    public Genres(String genres, List<Posts> posts) {
        this.genres = genres;
        this.posts = posts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public List<Posts> getPosts() {
        return posts;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }
}

