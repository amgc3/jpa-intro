package com.springbootframework.datapostgres.service;

import com.springbootframework.datapostgres.model.Actor;
import com.springbootframework.datapostgres.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {
    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> fetchActors() {
        return actorRepository.findAll();
    }

    public Optional<Actor> fetchActorById(Integer id) {
        return actorRepository.findById(id);
    }

    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }
}
