package com.springbootframework.datapostgres.controller;

import com.springbootframework.datapostgres.dto.ActorDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActorControllerTest {

    @Test
    void shouldReturnFullListOfActors(@Autowired TestRestTemplate template) {
        ResponseEntity<List> entity = template.getForEntity("/actors", List.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, entity.getHeaders().getContentType());
        List<ActorDTO> response = entity.getBody();
        assertNotNull(response);
        assertEquals(7, response.size());
        System.out.println(response); // this is an ArrayList
    }

    @Test
    void getActorById() {
    }

    @Test
    void updateActor() {
    }

    @Test
    void deleteActor() {
    }

    @Test
    void postActor() {
    }
}