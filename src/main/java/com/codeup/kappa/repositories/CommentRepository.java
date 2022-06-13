package com.codeup.kappa.repositories;

import com.codeup.kappa.models.Comment;
import com.codeup.kappa.models.Game;
import com.codeup.kappa.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
//    void deleteCommentByPost(Post post);

    void deleteAllByPost(Post post);

}
