package com.codeup.kappa.repositories;

import com.codeup.kappa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

//    HELP

//    @Query(value = "SELECT user_id FROM gamer_haven_db.user_followers WHERE follower_id = :id GROUP BY user_id ORDER BY COUNT(*) DESC;", nativeQuery = true)
//    List<User> followingList(@Param("id") long id);

//    HELP

}
