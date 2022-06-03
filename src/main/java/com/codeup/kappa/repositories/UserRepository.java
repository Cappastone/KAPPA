package com.codeup.kappa.repositories;

import com.codeup.kappa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT post_id FROM gamer_haven_db.post_likes t GROUP BY t.post_id ORDER BY COUNT(*) DESC;", nativeQuery = true)
    List<String> findByMostLiked();
}
