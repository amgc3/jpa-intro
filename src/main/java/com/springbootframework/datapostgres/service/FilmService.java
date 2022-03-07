package com.springbootframework.datapostgres.service;

import com.springbootframework.datapostgres.model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    Film saveFilm(Film film);
    List<Film> fetchFilms();
    Optional<Film> fetchFilmById(Integer id);
}
