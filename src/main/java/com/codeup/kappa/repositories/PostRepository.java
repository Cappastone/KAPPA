package com.codeup.kappa.repositories;

import com.codeup.kappa.models.Post;
import com.codeup.kappa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

//    @Query(value = "SELECT post_id, COUNT(*) AS magnitude FROM gamer_haven_db.post_likes t GROUP BY t.post_id ORDER BY magnitude DESC", nativeQuery = true)
//    List<User> findByMostLiked();

}
