package com.codeup.kappa.repositories;

import com.codeup.kappa.models.Game;
import com.codeup.kappa.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query(value = "SELECT game_id FROM gamer_haven_db.user_games t GROUP BY t.game_id ORDER BY COUNT(*) DESC;", nativeQuery = true)
    List<String> findGameByMostLiked();

//    Game getGameByGamesApiId(long id);



    Game getGameById(long id);


    @Query(value = "SELECT * FROM gamer_haven_db.games g WHERE g.id IN :ids", nativeQuery = true)
    List <Game> findGamesByIds(List <Long> ids);

    @Query(value = "SELECT * FROM gamer_haven_db.games g WHERE g.games_api_id = :api_id", nativeQuery = true)
    Game findGameByGamesApiId(long api_id);
//    List<Game> getGameByUserId(long id);

    @Query(value = "SELECT games_api_id FROM gamer_haven_db.games", nativeQuery = true)
    List <Long> findAllApiIds();


}
