package com.codeup.kappa.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "followers")
public class Followers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long user_id;

    @ManyToMany(mappedBy = "users")
    private List<Followers> follower_id;


    public Followers(){}

    public Followers(long user_id, List<Followers> follower_id) {
        this.user_id = user_id;
        this.follower_id = follower_id;
    }

    public Followers(long id, long user_id, List<Followers> follower_id) {
        this.id = id;
        this.user_id = user_id;
        this.follower_id = follower_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public List<Followers> getFollower_id() {
        return follower_id;
    }

    public void setFollower_id(List<Followers> follower_id) {
        this.follower_id = follower_id;
    }


}
