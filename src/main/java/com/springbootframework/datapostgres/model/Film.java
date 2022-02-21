package com.springbootframework.datapostgres.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name = "title")
    private String title;

    @NotEmpty
    @Column(name = "release_year")
    private int releaseYear;

    @NotEmpty
    @Column(name = "language")
    private String language;

    @Column(name = "runtime_minutes")
    private int runtimeMinutes;

    // add actor relationship to film
    @ManyToMany(mappedBy = "films")
    private List<Actor> actors;

    // good practice to implement equals and hashCode based on id value
    // to avoid possible problems with Hibernate further down the line
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(getId(), film.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getRuntimeMinutes() {
        return runtimeMinutes;
    }

    public void setRuntimeMinutes(int runtimeMinutes) {
        this.runtimeMinutes = runtimeMinutes;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
