package com.codeup.kappa.repositories;

import com.codeup.kappa.models.Post;
import com.codeup.kappa.models.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {

//    List<PostImage> getPostImageByPostId

    void deleteAllByPost(Post post);
}
