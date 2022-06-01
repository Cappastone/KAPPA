package com.codeup.kappa.repositories;

import com.codeup.kappa.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
