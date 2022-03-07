package com.springbootframework.datapostgres.service;

import com.springbootframework.datapostgres.model.Film;
import com.springbootframework.datapostgres.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    @Autowired
    private final FilmRepository filmRepository;

    public FilmService(final FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> fetchFilms() {
        return filmRepository.findAll();
    }

    public Optional<Film> fetchFilmById(Integer id) {
        return filmRepository.findById(id);
    }

    public Film saveFilm(Film film) {
        return filmRepository.save(film);
    }
}
