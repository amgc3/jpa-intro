package com.springbootframework.datapostgres.service.impl;

import com.springbootframework.datapostgres.model.Actor;
import com.springbootframework.datapostgres.repository.ActorRepository;
import com.springbootframework.datapostgres.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {
    private final ActorRepository actorRepository;

    @Autowired // can be omitted since only one constructor
    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> fetchActors() {
        return actorRepository.findAll();
    }

    public Optional<Actor> fetchActorById(Integer id) {
        return actorRepository.findById(id);
    }

    public Actor updateActorById(Integer id, Actor a) {
        Optional<Actor> actorToUpdateOptional = actorRepository.findById(id);
        if (!actorToUpdateOptional.isPresent()) {
            return null;
        }
        Actor actorToUpdate = actorToUpdateOptional.get();
        if (a.getFirstName() != null) {
            actorToUpdate.setFirstName(a.getFirstName());
        }
        if (a.getLastName() != null) {
            actorToUpdate.setLastName(a.getLastName());
        }
        if (a.getBirthDate() != null) {
            actorToUpdate.setBirthDate(a.getBirthDate());
        }
        if (a.getGender() != null) {
            actorToUpdate.setGender(a.getGender());
        }

        Actor updatedActor = this.actorRepository.save(actorToUpdate);
        return updatedActor;
    }

    public Actor deleteActorById(Integer id){
        Optional<Actor> actorOptional = this.actorRepository.findById(id);
        if (!actorOptional.isPresent()) {
            return null;
        } else {
            Actor actorToDelete = actorOptional.get();
            this.actorRepository.delete(actorToDelete);
            return actorToDelete;
        }
    }

    public Actor createActor(Actor actor) {
        return actorRepository.save(actor);
    }
}
