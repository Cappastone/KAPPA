# run this code BEFORE you run the Spring application =>
CREATE DATABASE IF NOT EXISTS gamer_haven_db;
# <= BEFORE

# run this code AFTER =>
USE gamer_haven_db;

INSERT INTO platform_links (discord, nintendo, playstation, twitch, xbox, youtube)
VALUES ('Gamer#1337', 'Gamer', 'Gamer-Man', 'GamerTTV', 'Gamer Man', 'Gamer-YT'),
       ('Noob#4269', 'Nooby', 'Nooby-Doo', 'Noobydoo42', 'Nooby Doo', 'Noobydoooo'),
       ('Random#0987', 'Rando', 'Random-1', 'random123', 'Rando Calrissian', 'Random1-YT'),
       ('Bigboss#1257', 'Boss', 'Big-Boss10', 'bigboss10', 'Big Boss10', 'BigBossman');

INSERT INTO users (bio, email, first_name, last_name, password, profile_picture_url, username, links_id)
VALUES ('I am a gamer who loves to game. Catch me on all major platforms pwning n00bs.', 'gamer@email.com', 'Bob', 'Gamerson', '$2a$10$Ll3qy8mo.g/D7xhCccotjetzJnubnArXG7yfjzlIV.BJmQsK4NXuG', 'https://nypost.com/wp-content/uploads/sites/2/2021/08/gaming-73.jpg?quality=75&strip=all', 'gamer', 1),
       ('I am new to video games and am looking for some cool people to game with!', 'n00b@email.com', 'Joe', 'Scrub', '$2a$10$8G6.ypWelMMS4pSOG.S.uuPvUGLEEaS3o5hKfEGD4aup8vZf8GlHy', 'https://metagameguides.com/wp-content/uploads/2022/02/Roblox-noob.png', 'nooby-doo', 2),
       ('I''m your neighborhood random that you love to hate!', 'random@email.com', 'Randall', 'Blueberry', '$2a$10$XRzqJmIR6bBWcFaGEzE0p.vaG/imhCTFfS4MYW5qJZB2xZk2bk5KW', 'https://static1.squarespace.com/static/5ac589eb8ab722aa77be2eeb/5b479b056d2a73336ed80ffe/5b50d290562fa7d2dabe2955/1532023444871/random_interactive_share.jpg', 'random', 3),
       ('I am the big Boss man.', 'biggestboss@email.com', 'Solid', 'Snake', '$2a$10$aYRSt091hnRo.ZTu8uwLqO/rK8pee4OTQ2kCGBN0/aQnna06p7aQK', 'https://static.tvtropes.org/pmwiki/pub/images/big_boss_1_877.jpg', 'bigboss', '4');

INSERT INTO user_followers (user_id, follower_id)
VALUES (1, 2),
       (1, 3),
       (1, 4),
       (3, 2),
       (4, 2);

INSERT INTO posts (body, user_id)
VALUES ('I was playing Apex Legends solo and dropped some crazy high kill games! Check out these screenshots and clips.', 1),
       ('I am pretty bad at Super Smash Bros but discovered a new character that I can see becoming my new main. What are your main characters?', 1),
       ('I was watching my little brother play Minecraft today and he accidentally found a super secret item! Check out these screenshots.', 1),
       ('I am new to Fortnite and looking for some cool people who can show me the ropes!', 2);

INSERT INTO post_images (title, url, post_id)
VALUES ('Apex Clip', 'filler url for video clip', 1),
       ('Apex Screenshot', 'filler url for screen shot', 1),
       ('Apex Screenshot 2', 'filler url for screen shot', 1),
       ('Minecraft Secret', 'filler url for screenshot', 3);

INSERT INTO post_comments (post_id, user_id, comment   # ,date
)
VALUES (1, 2, 'Wow, you stomped that lobby bro! Want to play?'
#, '2022-01-01'
),
       (1, 3, 'I can do that in my sleep...'
      # , '2022-01-02'
       ),
       (2, 2, 'I really like Yoshi'
       #, '2022-01-21'
       ),
       (2, 3, 'Link'
       #, '2022-01-22'
       ),
       (3, 2, 'Super cool'
       #, '2022-02-15'
       ),
       (3, 3, 'Old news'
       #, '2022-02-17'
       ),
       (4, 1, 'I can show you some tips and tricks. Feel free to add me on any platform!'
       #, '2022-03-05'
       ),
       (4, 3, 'Git gud'
       #, '2022-03-07'
       ),
       (4, 4, 'Solid!'
       #, '2022-03-08'
       );

INSERT INTO post_likes (user_id, post_id)
VALUES (1, 3),
       (1, 4),
       (2, 1),
       (2, 2),
       (2, 3),
       (2, 4),
       (3, 1),
       (4, 1);

INSERT INTO games (games_api_id, age_rating, description, developer, genre, image_url, platforms, title)
VALUES (3678, 'Teen', 'War Thunder is a free-to-play cross-platform vehicular combat MMO with more than 1000 playable aircraft, helicopters, tanks or ships sprawled over huge maps that feature real-life locations and battles that transpired over the course of 20th century, most notably during WWII.', 'Wargaming Group Limited', 'Action', 'https://media.rawg.io/media/games/d07/d0790809a13027251b6d0f4dc7538c58.jpg', 'PlayStation 4, Android, Xbox One, Microsoft Windows, Nintendo Switch, macOS, PlayStation 5, iOS, Xbox 360', 'War Thunder'),

       (722, 'Teen', 'World of Tanks - is a massively multiplayer game that revolves around the mid-20s century war machines. The gameplay is quite simple: the player takes control of one tank and joins others to participate in six basic types of battles. Gamers can choose from military vehicles from 1930-60s, the developers regularly update the fleet of vehicles. All combat units can be customized to your taste.', 'Gaijin Entertainment', 'Action', 'https://media.rawg.io/media/games/c3b/c3be1d5f55cb9324c97ccb7aaaf42ad4.jpg', 'PlayStation 4, Android, Xbox One, Microsoft Windows, Nintendo Switch, macOS, PlayStation 5, iOS, Xbox 360', 'World Of Tanks'),

       (248521, 'Teen', 'Valheim is a game about exploring a huge fantasy world inspired by norse mythology and viking culture. You start your adventure at the relatively peaceful center of Valheim. The further from the center you travel, the more challenging the world becomes. But you will also find more valuable materials that you can use to craft deadlier weapons and sturdier armor. You will also build your own viking strongholds and outposts all over the world. Eventually you will build a mighty longship and sail the great oceans in search of exotic lands … but be wary of sailing too far...', 'Iron Gate AB', 'Adventure', 'https://media.rawg.io/media/games/adb/adb59be81367b19c2544457424bcf086.jpg', 'Windows, Linux', 'Valheim'),

       (41571, 'Teen', 'A 5v5 character based competitive shooter from Riot Games. Available worldwide. Master dozens of different weapons and abilties and show your skill. Esports. New Maps. Competitive FPS.', 'Riot Games', 'Shooter', 'https://media.rawg.io/media/screenshots/3e0/3e0afea4771cd21aa3fb16f02e1ca108.jpg', 'Windows, Linux', 'Valorant'),

       (356714, 'Everyone', 'Join your crewmates in a multiplayer game of teamwork and betrayal!</p>
<p>Play online or over local wifi with 4-10 players as you attempt to hold your spaceship together and return back to civilization. But beware...as there may be an alien impostor aboard!', 'Innersloth', 'casual', 'https://media.rawg.io/media/games/e74/e74458058b35e01c1ae3feeb39a3f724.jpg', 'Android, iOS, PC, PS5, PS4, Xbox Series X|S, Xbox One, Nintendo Switch', 'Among Us'),

       (32, 'Teen', 'Destiny 2 is an online multiplayer first-person shooter. You take on the role of a Guardian that needs to protect the last city on Earth from alien invaders. The game follows its predecessor, Destiny. The goal of the game is to return the Light that was stolen from the Guardians by the aliens.', 'Bungie', 'Shooter', 'https://media.rawg.io/media/games/34b/34b1f1850a1c06fd971bc6ab3ac0ce0e.jpg', 'PlayStation 4, Xbox Series X and Series S, Google Stadia, Xbox One, PlayStation 5, Microsoft Windows', 'Destiny 2'),

       (290856, 'Teen', 'Conquer with character in Apex Legends, a free-to-play* Battle Royale shooter where legendary characters with powerful abilities team up to battle for fame and fortune on the fringes of the Frontier. Master an ever-growing roster of diverse legends, deep tactical squad play, and bold new innovations that level-up the Battle Royale experience—all within a rugged world where anything goes. Welcome to the next evolution of Battle Royale.', 'Respawn Entertainment', 'Shooter', 'https://media.rawg.io/media/games/b72/b7233d5d5b1e75e86bb860ccc7aeca85.jpg', 'Nintendo Switch, PlayStation 4, Xbox One, Xbox Series X and Series S, PlayStation 5, Android, Microsoft Windows, iOS', 'Apex Legends'),

       (47137, 'Teen', 'Fortnite Battle Royale is the completely free 100-player PvP mode in Fortnite. One giant map. A battle bus. Fortnite building skills and destructible environments combined with intense PvP combat. The last one standing wins.  Download now for FREE and jump into the action.', 'Epic Games', 'Shooter', 'https://media.rawg.io/media/games/dcb/dcbb67f371a9a28ea38ffd73ee0f53f3.jpg', 'Windows, macOS, Nintendo Switch, PlayStation 4, PlayStation 5, Xbox One, Xbox Series X/S, iOS, Android', 'Fortnite'),

       (10213, 'Everyone', 'What used to be an unofficial modded map for the Warcraft 3, ended up being the most budgeted cybersport discipline, gathering millions of people to watch annual international championships.', 'Valve-Software', 'Massively Multiplayer', 'https://media.rawg.io/media/games/83f/83f6f70a7c1b86cd2637b029d8b42caa.jpg', 'Microsoft Windows, macOS, Linux, Classic Mac OS', 'DOTA 2'),

       (11859, 'Everyone', 'TF2 is an objective based arena shooter with unique characters, representing different classes, acting as a staple of casual and competitive gaming for Steam.', 'Valve Software', 'Shooter', 'https://media.rawg.io/media/games/46d/46d98e6910fbc0706e2948a7cc9b10c5.jpg', 'Microsoft Windows, macOS, Linux, PlayStation 3, Macintosh operating systems, Xbox 360', 'Team Fortress 2'),

       (22508, 'Teen', 'The Legacy
Overwatch is a multiplayer first-person shooter from the company that gave players the saga of Azeroth, Starcraft and the Diablo universe.', 'Activision/Blizzard', 'Shooter', 'https://media.rawg.io/media/games/4ea/4ea507ceebeabb43edbc09468f5aaac6.jpg', 'PlayStation 4, Xbox One, Nintendo Switch, Microsoft Windows', 'Overwatch'),

       (4291, 'Mature', 'Counter-Strike is a multiplayer phenomenon in its simplicity. No complicated narratives to explain the source of the conflict, no futuristic and alien threats, but counter-terrorists against terrorists. Arena shooter at its core, CS:GO provides you with various methods of disposing enemies and reliant on cooperation within the team.', 'Valve Software', 'Shooter', 'https://media.rawg.io/media/games/736/73619bd336c894d6941d926bfd563946.jpg', 'Microsoft Windows, macOS, Xbox One, Linux, PlayStation 3, Xbox 360, Classic Mac OS', 'Counter Strike: Go'),

       (418467, 'Mature', '"Welcome to Warzone, the new massive combat arena within Call of Duty®: Modern Warfare®, free for everyone.', 'Raven Software', 'Shooter', 'https://media.rawg.io/media/games/7e3/7e327a055bedb9b6d1be86593bef473d.jpg', 'Xbox Series X and Series S, PlayStation 4, PlayStation 5, Microsoft Windows', 'Call Of Duty Warzone'),

       (3272, 'Everyone', 'Highly competitive soccer game with rocket-cars is the most comprehensive way to describe this game.', 'Psyonix', 'Sports', 'https://media.rawg.io/media/games/8cc/8cce7c0e99dcc43d66c8efd42f9d03e3.jpg', 'PlayStation 4, Nintendo Switch, Xbox One, Microsoft Windows, Macintosh operating systems, Linux', 'Rocket League'),

       (8488, 'Teen', 'The gameplay of the game is based on intense strategic battles between the Navigator and the defenders in a limited space. Locations in the game consist of a multi-level object (for example, a multi-storey building), inside which there are defenders, and the area around it, where the attackers begin.', 'Ubisoft', 'Shooter', 'https://media.rawg.io/media/games/b34/b3419c2706f8f8dbe40d08e23642ad06.jpg', 'Microsoft Windows PlayStation 4 PlayStation 5 Xbox One Xbox Series X/S', 'Tom Clancys Rainbow Six Siege 2'),

       (9997, 'Mature', 'Arma 3 is an open-world tactical shooter with RPG elements and a heavy focus on realism. The game is set on fictional Greece-themed islands in the near future during the war between NATO and Iran. In the campaign mode you take on the role of Corporal Ben Kerry, who takes part in this conflict. There are three chapters in the campaign but you can complete separate missions during the multiplayer mode.', 'Bohemia Interactive', 'Shooter', 'https://media.rawg.io/media/games/7a2/7a2d3c0883f01e43bff02f0d124d4642.jpg', 'Microsoft Windows', 'Arma 3'),

       (9882, 'Not Rated', 'Don''t Starve Together is surrealistic and grim survival game made up for six people simultaneously. Initially, Don''t Starve is a single-player game with a core mechanic of going through various problems of your character. You have to sleep and eat well. Also, you have to keep your mental health all right and don''t go completely mad at a situation, when these dreadful and twisted creatures are running towards you to tear apart. It seems like the world of Don''t Starve is heavily inspired by Tim Burton, having a very grotesque yet creepy and bizarre-looking monsters and locations with an atmospheric soundtrack. While heading to multiplayer, you will experience every single problem from a singles game.', 'Klei Entertainment', 'Action', 'https://media.rawg.io/media/games/dd5/dd50d4266915d56dd5b63ae1bf72606a.jpg', 'macOS, PlayStation 4, Xbox One, Microsoft Windows, Linux', 'Dont Starve Together'),

       (42336, 'Mature', 'Grand Theft Auto Online is a dynamic and persistent open world for 16 players that begins by sharing content and mechanics with Grand Theft Auto V, but continues to expand and evolve with content created by Rockstar and other players. Taking the fundamental concepts of open-world freedom, ambient activity and mission-based gameplay and making them available to multiple players in an incredibly dynamic online world, we offer players the freedom to explore alone or work cooperatively with friends to complete missions. Residents of Los Santos band together to participate in activities and ambient events, or compete in traditional game modes with the entire community, all with the personality and refined mechanics of Grand Theft Auto V.', 'Rockstar', 'Action', 'https://media.rawg.io/media/games/bbd/bbd7af2d6dab31e0b3bbfb5f63575ab8.jpg', 'PlayStation 4, Xbox One, Xbox Series X and Series S, PlayStation 5, PlayStation 3, Microsoft Windows, Xbox 360', 'Grand Theft Auto Online');

INSERT INTO user_games (user_id, game_id)
VALUES (1, 10),
       (1, 18),
       (1, 11),
       (1, 12),
       (1, 6),
       (1, 8),
       (2, 8),
       (2, 6),
       (2, 11),
       (3, 5),
       (3, 7),
       (3, 8),
       (3, 11),
       (3, 12),
       (3, 13),
       (3, 14),
       (3, 15);