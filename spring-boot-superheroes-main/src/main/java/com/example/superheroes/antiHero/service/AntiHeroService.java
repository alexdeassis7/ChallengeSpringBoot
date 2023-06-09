package com.example.superheroes.antiHero.service;

import com.example.superheroes.antiHero.contract.AntiHeroContract;
import com.example.superheroes.antiHero.entity.AntiHeroEntity;
import com.example.superheroes.antiHero.repository.AntiHeroRepository;
import com.example.superheroes.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class AntiHeroService implements AntiHeroContract {

    private final AntiHeroRepository repo = null;

    @Override
    public Iterable<AntiHeroEntity> findAllAntiHeroes() {
        return repo.findAll();
    }

    @Override
    public AntiHeroEntity findAntiHeroById(UUID id) {
        return findOrThrow(id);
    }

    @Override
    public void removeAntiHeroById(UUID id) {
        repo.deleteById(id);
    }

    @Override
    public AntiHeroEntity addAntiHero(AntiHeroEntity antiHero) {
        return repo.save(antiHero);
    }

    @Override
    public void updateAntiHero(UUID id, AntiHeroEntity antiHero) {
        findOrThrow(id);
        repo.save(antiHero);
    }

    private AntiHeroEntity findOrThrow(final UUID id) {
        return repo
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Anti-hero by id " + id + " was not found")
                );
    }
}
