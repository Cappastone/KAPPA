package com.codeup.kappa.repositories;

import com.codeup.kappa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(value = "SELECT t.user_id FROM gamer_haven_db.user_followers t WHERE t.follower_id = :user_id GROUP BY t.user_id", nativeQuery = true)
    List<Long> followingList(long user_id);

}
