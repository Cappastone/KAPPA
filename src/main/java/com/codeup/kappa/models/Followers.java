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

    @ManyToMany(mappedBy = "followers")
    private List<User> users;


    public Followers(){}

    public Followers(long id, long user_id, List<User> users) {
        this.id = id;
        this.user_id = user_id;
        this.users = users;
    }

    public Followers(long user_id, List<User> users) {
        this.user_id = user_id;
        this.users = users;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
