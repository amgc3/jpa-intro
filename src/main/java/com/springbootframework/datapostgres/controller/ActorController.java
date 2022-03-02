package com.springbootframework.datapostgres.controller;

import com.springbootframework.datapostgres.dto.ActorDTO;
import com.springbootframework.datapostgres.dto.FilmDTO;
import com.springbootframework.datapostgres.model.Actor;
import com.springbootframework.datapostgres.model.Film;
import com.springbootframework.datapostgres.service.ActorService;
import com.springbootframework.datapostgres.service.FilmService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/actors")
public class ActorController {

    private final ModelMapper modelMapper;
    private final ActorService actorService;
    private final FilmService filmService;

    public ActorController(ActorService actorService, FilmService filmService, ModelMapper modelMapper) {
        this.actorService = actorService;
        this.filmService = filmService;
        this.modelMapper = modelMapper;
    }

//    @GetMapping
//    public ResponseEntity<List<Actor>> getActors() {
//        return new ResponseEntity<>(this.actorService.fetchActors(), HttpStatus.OK);
//    }
    @GetMapping
    public ResponseEntity<List<ActorDTO>> getActors() {

    return new ResponseEntity<>(this.actorService.fetchActors()
            .stream()
            .map(actor -> modelMapper.map(actor, ActorDTO.class))
            .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getActorById(@PathVariable("id") Integer id) {
        Optional<Actor> actorOptional = this.actorService.fetchActorById(id);
        if (actorOptional.isPresent()) {
            Actor actor = actorOptional.get();
            return new ResponseEntity<>(modelMapper.map(actor, ActorDTO.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(String.format("Invalid id : %s ", id), HttpStatus.NOT_FOUND);
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Object> updateActor(@PathVariable("id") Integer id, @RequestBody Actor a) {
//        Actor actorToUpdate = this.actorService.updateActorById(id, a);
//        if (actorToUpdate != null) {
//            return new ResponseEntity<>(actorToUpdate, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(String.format("Invalid id : %s ", id), HttpStatus.NOT_FOUND);
//        }
//    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateActor(@PathVariable("id") Integer id, @RequestBody ActorDTO actorDTO) {
        // convert DTO to Entity
        Actor actor = modelMapper.map(actorDTO, Actor.class);
        Actor actorToUpdate = this.actorService.updateActorById(id, actor);
        if (actorToUpdate != null) {
            // return updatedActorDTO = modelMapper.map(actorToUpdate, ActorDTO.class)
            return new ResponseEntity<>(modelMapper.map(actorToUpdate, ActorDTO.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(String.format("Invalid id : %s ", id), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteActor(@PathVariable("id") Integer id) {
        Actor actorToDelete = this.actorService.deleteActorById(id);
        if (actorToDelete != null) {
            return new ResponseEntity<>(modelMapper.map(actorToDelete, ActorDTO.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(String.format("Invalid id : %s ", id), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/films")
    public ResponseEntity<List<FilmDTO>> getFilms() {
        return new ResponseEntity<>(this.filmService.fetchFilms()
                .stream()
                .map(film -> modelMapper.map(film, FilmDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity<Object> postActor(@Valid final @RequestBody Actor actor) {
        try {
            Actor savedActor = this.actorService.createActor(actor);
            return new ResponseEntity<>(modelMapper.map(savedActor, ActorDTO.class), HttpStatus.CREATED);
        } catch(Exception e) {
            System.out.println("saved actpr " + this.actorService.createActor(actor));
            return new ResponseEntity<>(String.format("Validation failed!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/films")
    public ResponseEntity<Object> postFilm(@Valid final @RequestBody Film film) {
        try {
            Film savedFilm = this.filmService.saveFilm(film);
            return new ResponseEntity<>(modelMapper.map(savedFilm, FilmDTO.class), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }
    }

}
