package com.springbootframework.datapostgres.dto;

import lombok.Data;

@Data
public class FilmDTO {
    private Integer id;
    private String title;
    private int releaseYear;
    private String language;
    private int runtimeMinutes;

}
