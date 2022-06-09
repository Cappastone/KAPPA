USE gamer_haven_db;

SELECT t.user_id FROM gamer_haven_db.user_followers t WHERE t.follower_id = 2 GROUP BY t.user_id;

SELECT games_api_id FROM gamer_haven_db.games;

SELECT *
FROM gamer_haven_db.posts
ORDER BY creation_date DESC;