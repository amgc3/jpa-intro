package com.springbootframework.datapostgres.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="actors")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // validates that property is not null or empty
    @NotEmpty
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birth_year")
    private int birthYear;

    // add film relationship to actors
    @ManyToMany
    @JoinTable(
            name = "actor_film",
            joinColumns = @JoinColumn(name = "actors_id"),
            inverseJoinColumns = @JoinColumn(name = "films_id"))
    private List<Film> films;

    // add city relationship to actors
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    // do we need the no args default constructor?
    protected Actor() {}

    public Actor(String firstName, String lastName, String gender, Integer birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthYear = birthYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(id, actor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }
}
