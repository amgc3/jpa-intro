package com.springbootframework.datapostgres.service.impl;

import com.springbootframework.datapostgres.model.Film;
import com.springbootframework.datapostgres.repository.FilmRepository;
import com.springbootframework.datapostgres.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private final FilmRepository filmRepository;

    public FilmServiceImpl(final FilmRepository filmRepository) {
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
