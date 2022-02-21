package com.springbootframework.datapostgres.controller;

import com.springbootframework.datapostgres.model.Actor;
import com.springbootframework.datapostgres.model.Film;
import com.springbootframework.datapostgres.service.ActorService;
import com.springbootframework.datapostgres.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.util.List;

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
        return new ResponseEntity<>(actorService.fetchActors(), HttpStatus.OK);
    }

    @GetMapping("actors/films")
    public ResponseEntity<List<Film>> getFilms() {
        return new ResponseEntity<>(filmService.fetchFilms(), HttpStatus.OK) ;
    }

    @PostMapping("/actors")
    public ResponseEntity<Actor> postActor(@Valid final @RequestBody Actor actor) {
        try {
            Actor savedActor = actorService.saveActor(actor);
            return new ResponseEntity<>(savedActor, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/actors/films")
    public ResponseEntity<Film> postFilm(final @RequestBody Film film) {
        try {
            Film savedFilm = filmService.saveFilm(film);
            return new ResponseEntity<>(savedFilm, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }
    }

}
