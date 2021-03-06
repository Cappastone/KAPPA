package com.codeup.kappa.repositories;

import com.codeup.kappa.models.Game;
import com.codeup.kappa.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long> {

//    @Query(value = "SELECT post_id FROM gamer_haven_db.post_likes t GROUP BY t.post_id ORDER BY COUNT(*) DESC;", nativeQuery = true)
//    List<Post> findByMostLiked();
//
//    @Query(value = "SELECT COUNT(*) FROM gamer_haven_db.post_likes t WHERE t.post_id = :id ;", nativeQuery = true)
//    Long numberOfLikes(long id);

    @Query(value = "SELECT post_id FROM gamerhaven_db.post_likes t GROUP BY t.post_id ORDER BY COUNT(*) DESC;", nativeQuery = true)
    List<String> findPostByMostLiked();

//    @Query(value = "SELECT COUNT(*) FROM gamer_haven_db.post_likes t WHERE t.post_id = :id ;", nativeQuery = true)
//    Long numberOfLikes(long id);

    Post getPostById(long id);

    // Replaced this method with a custom query
//    List<Post> getPostByUserId(long id);

    @Query(value = "SELECT * FROM gamerhaven_db.posts g WHERE g.user_id = :id ORDER BY g.creation_date DESC", nativeQuery = true)
    List<Post> findPostsByUserId(long id);


    @Query(value = "SELECT * FROM gamerhaven_db.posts g WHERE g.user_id IN :ids ORDER BY g.creation_date DESC", nativeQuery = true)
    List<Post> findPostsByUserIds(List<Long> ids);

    @Modifying
    @Query(value = "DELETE FROM gamerhaven_db.post_likes g WHERE g.post_id = :id", nativeQuery = true)
    void deletePostLikeByPostId(long id);

//    @Query("SELECT FROM gamerhaven_db.", nativeQuery = true)
//    Long getUserIdById(long id);

}

