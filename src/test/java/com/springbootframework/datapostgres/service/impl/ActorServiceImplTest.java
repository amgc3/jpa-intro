package com.springbootframework.datapostgres.service.impl;

import com.springbootframework.datapostgres.model.Actor;
import com.springbootframework.datapostgres.model.City;
import com.springbootframework.datapostgres.model.Film;
import com.springbootframework.datapostgres.repository.ActorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ActorServiceImplTest {

    private ActorRepository actorRepository;
    private ActorServiceImpl actorService;

    private List<Film> films = new ArrayList<>();
    Actor actor1 = new Actor(1, "Mick", "Jordan", Actor.ActorGender.M, LocalDate.parse("1992-12-23"), films, new City(1, "London", "UK"));
    Actor actor2 = new Actor(2, "Rosalinda", "Celentano", Actor.ActorGender.F, LocalDate.parse("1968-07-15"), films, new City(2, "Rome", "IT"));
    Actor actor3 = new Actor(3, "Ernesto", "Alterio", Actor.ActorGender.M, LocalDate.parse("1970-07-23"), films, new City(3, "Madrid", "ES"));

    @BeforeEach
    void setUp() {
        // mock repository
        actorRepository = mock(ActorRepository.class);
        actorService = new ActorServiceImpl(actorRepository);
    }

    @Test
    void should_return_all_actors() {

        List<Actor> actors = new ArrayList<>(Arrays.asList(actor1, actor2, actor3));
        when(actorRepository.findAll()).thenReturn(actors);
        assertEquals(3, actorService.fetchActors().size());
        assertEquals( actors, actorService.fetchActors());

    }

    @Test
    void fetchActorById() {
    }

    @Test
    void updateActorById() {
    }

    @Test
    void deleteActorById() {
    }

    @Test
    void createActor() {
    }
}