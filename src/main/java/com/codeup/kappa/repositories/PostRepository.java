package com.codeup.kappa.repositories;

import com.codeup.kappa.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT post_id FROM gamer_haven_db.post_likes t GROUP BY t.post_id ORDER BY COUNT(*) DESC;", nativeQuery = true)
    List<String> findByMostLiked();

    @Query(value = "SELECT COUNT(*) FROM gamer_haven_db.post_likes t WHERE t.post_id = :id ;", nativeQuery = true)
    Long numberOfLikes(long id);

    Post getPostById(long id);
}
