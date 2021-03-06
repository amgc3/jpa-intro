package com.springbootframework.datapostgres.controller;

import com.springbootframework.datapostgres.dto.ActorDTO;
import com.springbootframework.datapostgres.dto.FilmDTO;
import com.springbootframework.datapostgres.model.Actor;
import com.springbootframework.datapostgres.model.Film;
import com.springbootframework.datapostgres.service.ActorService;
import com.springbootframework.datapostgres.service.FilmService;
import com.springbootframework.datapostgres.service.impl.ActorServiceImpl;
import com.springbootframework.datapostgres.service.impl.FilmServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor //takes care of constructor injection
@RestController
@RequestMapping("/actors")
public class ActorController {

    private final ModelMapper modelMapper;
    private final ActorService actorService;
    private final FilmService filmService;

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
    public ResponseEntity<String> deleteActor(@PathVariable("id") Integer id) {
        this.actorService.deleteActorById(id);
        return new ResponseEntity<>(String.format("Actor with id : %s deleted successfully", id), HttpStatus.OK);
    }

    @GetMapping("/films")
    public ResponseEntity<List<FilmDTO>> getFilms() {
        return new ResponseEntity<>(this.filmService.fetchFilms()
                .stream()
                .map(film -> modelMapper.map(film, FilmDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK) ;
    }

    @GetMapping("/films/{id}")
    public ResponseEntity<Object> getFilmById(@PathVariable("id") Integer id) {
        Optional<Film> filmOptional = filmService.fetchFilmById(id);
        if (filmOptional.isPresent()) {
            Film film = filmOptional.get();
            return new ResponseEntity<>(modelMapper.map(film, FilmDTO.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(String.format("Invalid id : %s ", id), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<Object> postActor(@Valid final @RequestBody Actor actor) {
        try {
            Actor savedActor = this.actorService.createActor(actor);
            return new ResponseEntity<>(modelMapper.map(savedActor, ActorDTO.class), HttpStatus.CREATED);
        } catch(Exception e) {
            System.out.println("saved actor " + this.actorService.createActor(actor));
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
