package com.springbootframework.datapostgres.controller;

import com.springbootframework.datapostgres.model.Actor;
import com.springbootframework.datapostgres.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActorController {

    @Autowired
    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/actors")
    public ResponseEntity<List<Actor>> getActors() {
        return new ResponseEntity<>(actorService.fetchActors(), HttpStatus.OK);
    }

    @PostMapping("/actors")
    public ResponseEntity<Actor> postActor(final @RequestBody Actor actor) {
        try {
            Actor savedActor = actorService.saveActor(
                    new Actor(actor.getFirstName(), actor.getLastName(), actor.getGender(), actor.getBirthYear())
            );
            return new ResponseEntity<>(savedActor, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }
    }

}
