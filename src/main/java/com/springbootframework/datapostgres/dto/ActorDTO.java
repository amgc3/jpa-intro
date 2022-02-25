package com.springbootframework.datapostgres.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ActorDTO {
    private enum ActorGender {
        M,
        F
    }
    private Integer id;
    private ActorGender gender;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

}
