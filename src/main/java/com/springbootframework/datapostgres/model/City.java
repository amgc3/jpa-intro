package com.springbootframework.datapostgres.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "cities")

// Causes lombok to generate toString(), equals(), hashCode(), getter() & setter(), and Required arguments constructor in one go
@Data
// Causes Lombok to implement the Builder design pattern for the Pojo class
@Builder
// Causes Lombok to generate a constructor with no parameters.
@NoArgsConstructor
// // Causes Lombok to generate a constructor with 1 parameter for each field in your class.
@AllArgsConstructor
// Spring framework annotation
@Component
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    @NotEmpty
    @Column(name = "city_name")
    private String cityName;

    @NotEmpty
    @Column(name = "country_code")
    private String countryCode;

//    @OneToMany(mappedBy = "city")
//    private List<Actor> actors;

//    public List<Actor> getActors() {
//        return actors;
//    }
//
//    public void setActors(List<Actor> actors) {
//        this.actors = actors;
//    }

}
