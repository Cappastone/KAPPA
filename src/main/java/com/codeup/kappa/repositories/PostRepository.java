package com.codeup.kappa.repositories;

import com.codeup.kappa.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
