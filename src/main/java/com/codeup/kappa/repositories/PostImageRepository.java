package com.codeup.kappa.repositories;

import com.codeup.kappa.models.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
}
