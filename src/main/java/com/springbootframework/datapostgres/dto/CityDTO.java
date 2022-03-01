package com.springbootframework.datapostgres.dto;

import lombok.Data;

@Data
public class CityDTO {
    private Integer id;
    private String cityName;
    private String countryCode;
}
