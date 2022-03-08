package com.springbootframework.datapostgres.service;

import com.springbootframework.datapostgres.model.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorService {
    Actor createActor(Actor actor);
    List<Actor> fetchActors();
    Optional<Actor> fetchActorById(Integer id);
    Actor updateActorById(Integer id, Actor a);
    void deleteActorById(Integer id);
}
