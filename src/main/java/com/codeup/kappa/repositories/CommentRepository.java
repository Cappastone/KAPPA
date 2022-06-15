package com.codeup.kappa.repositories;

import com.codeup.kappa.models.Comment;
import com.codeup.kappa.models.Game;
import com.codeup.kappa.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
//    void deleteCommentByPost(Post post);

    void deleteAllByPost(Post post);

    @Query(value = "SELECT id FROM gamerhaven_db.post_comments t WHERE t.user_id = :id", nativeQuery = true)
    List<Long> findCommentIdsByUserId(long id);

}
