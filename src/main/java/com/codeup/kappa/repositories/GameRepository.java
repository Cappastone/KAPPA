package com.codeup.kappa.repositories;

import com.codeup.kappa.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query(value = "SELECT game_id FROM gamer_haven_db.user_games t GROUP BY t.game_id ORDER BY COUNT(*) DESC;", nativeQuery = true)
    List<String> findByMostLiked();

    Game getGameById(long id);

}
