package com.springbootframework.datapostgres.service;

import com.springbootframework.datapostgres.model.Actor;
import com.springbootframework.datapostgres.model.Film;
import com.springbootframework.datapostgres.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {
    private final FilmRepository filmRepository;

    public FilmService(final FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> fetchFilms() {
        return filmRepository.findAll();
    }

    public Film saveFilm(Film film) {
        return filmRepository.save(film);
    }
}
