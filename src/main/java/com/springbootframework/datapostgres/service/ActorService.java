package com.springbootframework.datapostgres.service;

import com.springbootframework.datapostgres.model.Actor;
import com.springbootframework.datapostgres.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        if (a.getBirthYear() != null) {
            actorToUpdate.setBirthYear(a.getBirthYear());
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

    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }
}
