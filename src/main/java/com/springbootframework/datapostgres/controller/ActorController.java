package com.springbootframework.datapostgres.controller;

import com.springbootframework.datapostgres.model.Actor;
import com.springbootframework.datapostgres.model.Film;
import com.springbootframework.datapostgres.service.ActorService;
import com.springbootframework.datapostgres.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ActorController {

    @Autowired
    private final ActorService actorService;

    @Autowired
    private final FilmService filmService;

    public ActorController(ActorService actorService, FilmService filmService) {
        this.actorService = actorService;
        this.filmService = filmService;
    }

    @GetMapping("/actors")
    public ResponseEntity<List<Actor>> getActors() {
        return new ResponseEntity<>(this.actorService.fetchActors(), HttpStatus.OK);
    }

    @GetMapping("/actors/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable("id") Integer id) {
        Optional<Actor> actorOptional = this.actorService.fetchActorById(id);
        if (actorOptional.isPresent()) {
            Actor actor = actorOptional.get();
            return new ResponseEntity<>(actor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/actors/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable("id") Integer id, @RequestBody Actor a) {
        Actor actorToUpdate = this.actorService.updateActorById(id, a);
        if (actorToUpdate != null) {
            return new ResponseEntity<>(actorToUpdate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/actors/{id}")
    public ResponseEntity<Actor> deleteActor(@PathVariable("id") Integer id) {
        Actor actorToDelete = this.actorService.deleteActorById(id);
        if (actorToDelete != null) {
            return new ResponseEntity<>(actorToDelete, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("actors/films")
    public ResponseEntity<List<Film>> getFilms() {
        return new ResponseEntity<>(this.filmService.fetchFilms(), HttpStatus.OK) ;
    }

    @PostMapping("/actors")
    public ResponseEntity<Actor> postActor(@Valid final @RequestBody Actor actor) {
        try {
            Actor savedActor = this.actorService.saveActor(actor);
            return new ResponseEntity<>(savedActor, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/actors/films")
    public ResponseEntity<Film> postFilm(@Valid final @RequestBody Film film) {
        try {
            Film savedFilm = this.filmService.saveFilm(film);
            return new ResponseEntity<>(savedFilm, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }
    }

}
