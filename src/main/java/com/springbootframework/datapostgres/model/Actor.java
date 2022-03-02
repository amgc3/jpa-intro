package com.springbootframework.datapostgres.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="actors")
// Causes lombok to generate toString(), equals(), hashCode(), getter() & setter(), and Required arguments constructor in one go
@Data
// Causes Lombok to implement the Builder design pattern for the Pojo class
// TODO look into @Builder annotation and implications
@Builder
// Causes Lombok to generate a constructor with no parameters, @Data would if no other constructor was present
// but we have @AllArgsConstructor, so we need it
@NoArgsConstructor
// // Causes Lombok to generate a constructor with 1 parameter for each field in your class.
@AllArgsConstructor
// Spring framework annotation
@Component
public class Actor {

    private enum ActorGender {
        M,
        F
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // validates that property is not null or empty
    @NotEmpty
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private ActorGender gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    // add film relationship to actors
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "actor_film",
            joinColumns = @JoinColumn(name = "actors_id"),
            inverseJoinColumns = @JoinColumn(name = "films_id"))
    private List<Film> films;

    // add city relationship to actors Many actors can exist ToOne city
    @ManyToOne
    // there will be a column named city_id to reference the city the actor was born in
    // this is the foreign key
    @JoinColumn(name = "city_id")
    private City city;


//
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Actor actor = (Actor) o;
//        return Objects.equals(id, actor.id);
//    }
//
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//
//    public String toString() {
//        return "Actor{" +
//                "id=" + id +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", gender='" + gender + '\'' +
//                ", birthYear=" + birthYear +
//                '}';
//    }
}
