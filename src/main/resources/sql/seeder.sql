# run this code BEFORE you run the Spring application =>
CREATE DATABASE IF NOT EXISTS gamer_haven_db;
# <= BEFORE

# run this code AFTER =>
USE gamer_haven_db;

INSERT INTO platform_links(discord, nintendo, playstation, twitch, xbox, youtube)
VALUES ('Gamer#1337', 'Gamer', 'Gamer-Man', 'GamerTTV', 'Gamer Man', 'Gamer-YT'),
       ('Noob#4269', 'Nooby', 'Nooby-Doo', 'noobydoo42', 'Nooby Doo', 'Noobydoooo'),
       ('Random#0987', 'Rando', 'Random-1', 'random123', 'Rando Calrissian', 'Random1-YT');

INSERT INTO users (bio, email, first_name, last_name, password, profile_picture, username, links_id)
VALUES ('I am a gamer who loves to game. Catch me on all major platforms pwning n00bs.', 'gamer@email.com', 'Bob', 'Gamerson', 'password', 'https://nypost.com/wp-content/uploads/sites/2/2021/08/gaming-73.jpg?quality=75&strip=all', 'gamer', 1),
       ('I am new to video games and am looking for some cool people to game with!', 'n00b@email.com', 'Joe', 'Scrub', 'password', 'https://metagameguides.com/wp-content/uploads/2022/02/Roblox-noob.png', 'nooby-doo', 2),
       ('I''m your neighborhood random that you love to hate!', 'random@email.com', 'Randall', 'Blueberry', 'password', 'https://static1.squarespace.com/static/5ac589eb8ab722aa77be2eeb/5b479b056d2a73336ed80ffe/5b50d290562fa7d2dabe2955/1532023444871/random_interactive_share.jpg', 'random', 3);

INSERT INTO user_followers (user_id, follower_id)
VALUES (1, 2),
       (1, 3),
       (3, 2);

# INSERT INTO favorite_games(api_id, title, user_id)
# VALUES (123, 'Halo', 1),
#        (124, 'Call of Duty', 1),
#        (125, 'Minecraft', 1),
#        (126, 'Fortnite', 1),
#        (127, 'Super Smash Bros', 1),
#        (128, 'Apex Legends', 1);
#
# INSERT INTO favorite_games(title)
# VALUES ('Halo'),
#        ('Call of Duty'),
#        ('Minecraft'),
#        ('Fortnite'),
#        ('Super Smash Bros'),
#        ('Apex Legends');

# INSERT INTO games_users(user_id, game_id)
# VALUES (1, 1),
#        (1, 2),
#        (1, 3),
#        (1, 6),
#        (2, 3),
#        (2, 5),
#        (3, 6);

INSERT INTO posts(body, title, user_id)
VALUES ('I was playing Apex Legends solo and dropped some crazy high kill games! Check out these screenshots and clips.', 'Apex Predator', 1),
       ('I am pretty bad at Super Smash Bros but discovered a new character that I can see becoming my new main. What are your main characters?', 'What''s your main?', 1),
       ('I was watching my little brother play Minecraft today and he accidentally found a super secret item! Check out these screenshots.', 'Minecraft Secret', 1),
       ('I am new to Fortnite and looking for some cool people who can show me the ropes!', 'Looking For Group', 2);

INSERT INTO post_images(title, url, post_id)
VALUES ('Apex Clip', 'filler url for video clip', 1),
       ('Apex Screenshot', 'filler url for screen shot', 1),
       ('Apex Screenshot 2', 'filler url for screen shot', 1),
       ('Minecraft Secret', 'filler url for screenshot', 3);

INSERT INTO comments (post_id, user_id, comment)
VALUES (1, 2, 'Wow, you stomped that lobby bro! Want to play?'),
       (1, 3, 'I can do that in my sleep...'),
       (2, 2, 'I really like Yoshi'),
       (2, 3, 'Link'),
       (3, 2, 'Super cool'),
       (3, 3, 'Old news'),
       (3, 1, 'I can show you some tips and tricks. Feel free to add me on any platform!'),
       (4, 3, 'Git gud');

INSERT INTO games(games_api_id, age_rating, description, developer, genre, image_url, platforms, title)
VALUES (1, 'E', 'Jump into the free-to-play team-based shooter with an ever-expanding roster of historical vehicles, stunning graphics, spectacular locales, and orchestral scores. Show your mastery and face other players in thrilling PvP clashes. A unique mix of strategy and action awaits!', 'Wargaming Group Limited', 'Action', 'https://cdn.akamai.steamstatic.com/steamcommunity/public/images/clans/39263509/bf9b969ec4acf760a0134bd0f5821bef482a7541.jpg', 'PlayStation 4, Android, Xbox One, Microsoft Windows, Nintendo Switch, macOS, PlayStation 5, iOS, Xbox 360', 'World Of Tanks'),
       (2, 'T', 'War Thunder is the most comprehensive free-to-play, cross-platform, MMO military game dedicated to aviation, armoured vehicles, and naval craft, from the early 20th century to the most advanced modern combat units. Join now and take part in major battles on land, in the air, and at sea.', 'Gaijin Entertainment', 'Action', 'https://static-cdn.jtvnw.net/ttv-boxart/War%20Thunder.jpg', 'Windows, Linux, Mac, PlayStation®4, PlayStation®5, Xbox One and Xbox Series X|S', 'War Thunder'),
       (3, 'T', 'A brutal exploration and survival game for 1-10 players, set in a procedurally-generated purgatory inspired by viking culture. Battle, build, and conquer your way to a saga worthy of Odin’s patronage!', '
Iron Gate AB', 'Adventure', 'https://assets-prd.ignimgs.com/2022/01/14/valheim-sq-1642187529813.jpg', 'Windows, Linux', 'Valheim'),
       (4, 'T', 'A 5v5 character based competitive shooter from Riot Games. Available worldwide. Master dozens of different weapons and abilties and show your skill. Esports. New Maps. Competitive FPS.', 'Riot Games', 'Shooter', 'https://cdn.pocket-lint.com/r/s/970x/assets/images/152432-games-feature-what-is-valorant-a-guide-to-the-free-to-play-fps-with-tips-on-how-to-win-image3-muha6tfgev-jpg.webp', 'Windows, Linux', 'Valorant'),
       (5, 'E', )