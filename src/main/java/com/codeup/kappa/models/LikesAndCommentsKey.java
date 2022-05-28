package com.codeup.kappa.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

//this class is needed to add the comment row to the join table =>
@Embeddable
public class LikesAndCommentsKey implements Serializable {

    @Column(name = "user_id")
    long userId;

    @Column(name = "post_id")
    long postId;

    public LikesAndCommentsKey(){}

    public LikesAndCommentsKey(long userId, long postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }
}
