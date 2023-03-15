package com.example.superheroes.hero.repository;

import com.example.superheroes.hero.model.Hero;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HeroRepository extends JpaRepository<Hero, UUID> {
	Optional<List<Hero>> findByfirstNameContainingIgnoreCase(String name);
}
