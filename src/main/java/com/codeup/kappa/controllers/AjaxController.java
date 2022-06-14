package com.codeup.kappa.controllers;


import com.codeup.kappa.models.Comment;
import com.codeup.kappa.models.Game;
import com.codeup.kappa.models.Post;
import com.codeup.kappa.models.User;
import com.codeup.kappa.repositories.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ajax")
public class AjaxController {

    private final UserRepository userDao;
    private final PostRepository postDao;
    private final PostImageRepository postImageDao;
    private final GameRepository gameDao;
    private final PasswordEncoder passwordEncoder;
    private final CommentRepository commentDao;


    public AjaxController(UserRepository userDao, PostRepository postDao, PostImageRepository postImageDao, GameRepository gameDao, PasswordEncoder passwordEncoder, CommentRepository commentDao) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.postImageDao = postImageDao;
        this.gameDao = gameDao;
        this.passwordEncoder = passwordEncoder;
        this.commentDao = commentDao;
    }

    private JsonNode stringToJsonNode(String data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonParser parser = factory.createParser(data);
        JsonNode actualObj = mapper.readTree(parser);

        return actualObj;
    }

    @PostMapping("/like-post")
    public Object LikePost(@RequestBody String data) throws IOException {

        JsonNode actualObj = stringToJsonNode(data);

        long user_id = actualObj.get("user_id").asLong();
        long post_id = actualObj.get("post_id").asLong();

        User user = userDao.getById(user_id);
        Post post = postDao.getById(post_id);
        List<Post> likedPosts = user.getLikedPosts();

        if (likedPosts.contains(post)) {
            System.out.println("Post already liked");
        } else {
            System.out.println("Post LIKED!");
            likedPosts.add(post);
            user.setLikedPosts(likedPosts);
            userDao.save(user);


        }
        return new Post();
    }

    @PostMapping("/unlike-post")
    public Object unlikePost(@RequestBody String data) throws IOException {

        JsonNode actualObj = stringToJsonNode(data);

        long user_id = actualObj.get("user_id").asLong();
        long post_id = actualObj.get("post_id").asLong();

        User user = userDao.getById(user_id);
        Post post = postDao.getById(post_id);
        List<Post> likedPosts = user.getLikedPosts();

        if (likedPosts.contains(post)) {
            likedPosts.remove(post);
            user.setLikedPosts(likedPosts);
            userDao.save(user);
            System.out.println("Post UNLIKED!");
        } else {
            System.out.println("Post already unliked");

        }
        return new Post();
    }


    @PostMapping("/favorite-game")
    public Object FavoriteGame(@RequestBody String data) throws IOException {

        JsonNode actualObj = stringToJsonNode(data);

        long user_id = actualObj.get("user_id").asLong();
        long game_id = actualObj.get("game_id").asLong();

        User user = userDao.getById(user_id);
        Game game = gameDao.getById(game_id);
        List<Game> favoriteGames = user.getFavoriteGames();

        if (favoriteGames.contains(game)) {
            System.out.println("Game already favorited");
        } else {
            System.out.println("Game FAVORITED!");
            favoriteGames.add(game);
            user.setFavoriteGames(favoriteGames);
            userDao.save(user);


        }
        return new Game();
    }

    @PostMapping("/unfavorite-game")
    public Object unFavoriteGame(@RequestBody String data) throws IOException {

        JsonNode actualObj = stringToJsonNode(data);

        long user_id = actualObj.get("user_id").asLong();
        long game_id = actualObj.get("game_id").asLong();

        User user = userDao.getById(user_id);
        Game game = gameDao.getById(game_id);
        List<Game> favoriteGames = user.getFavoriteGames();

        if (favoriteGames.contains(game)) {
            favoriteGames.remove(game);
            user.setFavoriteGames(favoriteGames);
            userDao.save(user);
            System.out.println("Game UNFAVORITED!");
        } else {
            System.out.println("Game already unfavorited");

        }
        return new Game();
    }


    @PostMapping("/follow-user")
    public Object FollowUser(@RequestBody String data) throws IOException {

        JsonNode actualObj = stringToJsonNode(data);

        long user_id = actualObj.get("user_id").asLong();
        long follower_id = actualObj.get("follower_id").asLong();

        User user = userDao.getById(user_id);
        User follower = userDao.getById(follower_id);
        List<User> followers = user.getFollowers();


        if (followers.contains(follower)) {
            System.out.println("User already followed");
        } else {
            System.out.println("FOLLOWING user!");
            followers.add(follower);
            user.setFollowers(followers);
            userDao.save(user);


        }
        return new User();
    }

    @PostMapping("/unfollow-user")
    public Object unFollowUser(@RequestBody String data) throws IOException {

        JsonNode actualObj = stringToJsonNode(data);

        long user_id = actualObj.get("user_id").asLong();
        long follower_id = actualObj.get("follower_id").asLong();

        User user = userDao.getById(user_id);
        User follower = userDao.getById(follower_id);
        List<User> followers = user.getFollowers();

        if (followers.contains(follower)) {
            followers.remove(follower);
            user.setFollowers(followers);
            userDao.save(user);
            System.out.println("User UNFOLLOWED!");
        } else {
            System.out.println("User already unfollowed");

        }
        return new User();
    }


    @PostMapping("/post-comment")
    public Object postComment(@RequestBody String data) throws IOException {

        JsonNode actualObj = stringToJsonNode(data);

        long user_id = actualObj.get("user_id").asLong();
        long post_id = actualObj.get("post_id").asLong();
        String comment_data = actualObj.get("comment_data").asText();

        Comment comment = new Comment();
        comment.setUser(userDao.getById(user_id));
        comment.setPost(postDao.getPostById(post_id));
        comment.setBody(comment_data);
        commentDao.save(comment);

        return new Comment();
    }


    @PostMapping("/delete-comment")
    public Object deleteComment(@RequestBody String data) throws IOException {

        JsonNode actualObj = stringToJsonNode(data);
        long comment_id = actualObj.get("comment_id").asLong();
        commentDao.deleteById(comment_id);

        return new Comment();
    }








}

