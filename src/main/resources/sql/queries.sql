CREATE DATABASE gamerhaven_db;

SELECT t.user_id
FROM gamerhaven_db.user_followers t
WHERE t.follower_id = 2
GROUP BY t.user_id;

SELECT games_api_id
FROM gamerhaven_db.games;