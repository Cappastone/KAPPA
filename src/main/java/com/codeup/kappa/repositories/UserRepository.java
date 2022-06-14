package com.codeup.kappa.repositories;

import com.codeup.kappa.models.Post;
import com.codeup.kappa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(value = "SELECT t.user_id FROM gamerhaven_db.user_followers t WHERE t.follower_id = :user_id GROUP BY t.user_id", nativeQuery = true)
    List<Long> followingList(long user_id);

    @Query(value = "SELECT post_id FROM gamerhaven_db.post_likes t WHERE t.user_id = :id", nativeQuery = true)
    List<Long> findPostIdLikedByUserId(long id);

    @Query(value = "SELECT game_id FROM gamerhaven_db.user_games t WHERE t.user_id = :id", nativeQuery = true)
    List<Long> findGameIdFavoriteByUserId(long id);

    @Query(value = "SELECT user_id FROM gamerhaven_db.user_followers t WHERE t.follower_id = :id", nativeQuery = true)
    List<Long> findUserIdsByFollowerId(long id);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

}
