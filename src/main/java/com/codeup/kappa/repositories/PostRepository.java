package com.codeup.kappa.repositories;

import com.codeup.kappa.models.Game;
import com.codeup.kappa.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

//    @Query(value = "SELECT post_id FROM gamer_haven_db.post_likes t GROUP BY t.post_id ORDER BY COUNT(*) DESC;", nativeQuery = true)
//    List<Post> findByMostLiked();
//
//    @Query(value = "SELECT COUNT(*) FROM gamer_haven_db.post_likes t WHERE t.post_id = :id ;", nativeQuery = true)
//    Long numberOfLikes(long id);

    @Query(value = "SELECT post_id FROM gamer_haven_db.post_likes t GROUP BY t.post_id ORDER BY COUNT(*) DESC;", nativeQuery = true)
    List<String> findPostByMostLiked();

//    @Query(value = "SELECT COUNT(*) FROM gamer_haven_db.post_likes t WHERE t.post_id = :id ;", nativeQuery = true)
//    Long numberOfLikes(long id);

    Post getPostById(long id);

    List<Post> getPostByUserId(long id);

    Post findPostByUserId(long id);

//    List<Post> findAllByUserId(List <Long> ids);

//    @Query(value = "SELECT * FROM gamer_haven_db.posts g WHERE g.id = :ids ORDER BY g.creation_date", nativeQuery = true)
//    List<Post>findPostsByIds(List<Long> ids);

}
