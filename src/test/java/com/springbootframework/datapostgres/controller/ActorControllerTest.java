package com.springbootframework.datapostgres.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootframework.datapostgres.dto.ActorDTO;
import com.springbootframework.datapostgres.model.Actor;
import com.springbootframework.datapostgres.model.City;
import com.springbootframework.datapostgres.model.Film;
import com.springbootframework.datapostgres.service.ActorService;
import com.springbootframework.datapostgres.service.FilmService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@WebMvcTest(ActorController.class)
public class ActorControllerTest {

    @Autowired
    private ActorController actorController;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    public ActorService actorService;

    @MockBean
    public FilmService filmService;

    private List<Film> films = new ArrayList<>();
    Actor actor1 = new Actor(1, "Mick", "Jordan", Actor.ActorGender.M, LocalDate.parse("1992-12-23"), films, new City(1, "London", "UK"));
    Actor actor2 = new Actor(2, "Rosalinda", "Celentano", Actor.ActorGender.F, LocalDate.parse("1968-07-15"), films, new City(2, "Rome", "IT"));
    Actor actor3 = new Actor(3, "Ernesto", "Alterio", Actor.ActorGender.M, LocalDate.parse("1970-07-23"), films, new City(3, "Madrid", "ES"));
    List<Actor> actors = new ArrayList<>(Arrays.asList(actor1, actor2, actor3));


//    @Test
//    void shouldReturnFullListOfActors(@Autowired TestRestTemplate template) {
//        ResponseEntity<List> entity = template.getForEntity("/actors", List.class);
//        assertEquals(HttpStatus.OK, entity.getStatusCode());
//        assertEquals(MediaType.APPLICATION_JSON, entity.getHeaders().getContentType());
//        List<ActorDTO> response = entity.getBody();
//        assertNotNull(response);
//        assertEquals(7, response.size());
//        System.out.println(response); // this is an ArrayList
//    }
    @Test
    void should_return_complete_list_of_actors() throws Exception {
        when(actorService.fetchActors()).thenReturn((actors));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/actors"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("Mick"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Jordan"));

    }


    @Test
    void should_return_actor_with_given_id() throws Exception {
        when(actorService.fetchActorById(anyInt())).thenReturn(Optional.ofNullable(actor1));
        // create a mock HTTP request to verify the expected result
        mockMvc.perform(MockMvcRequestBuilders.get("/actors/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Mick"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Jordan"))
                .andExpect(status().isOk());

    }

    @Test
    void updateActor() {
    }

    @Test
    void deleteActor() {
    }

    @Test
    void postActor() throws Exception {
        // mock user data that we want to save
        Actor actor = new Actor(7, "Rob", "Jordan", Actor.ActorGender.M, LocalDate.parse("1995-12-23"), films, new City(1, "London", "UK"));
        // mock request "/actor"
        when(actorService.createActor(any(Actor.class))).thenReturn(actor);
        mockMvc.perform(MockMvcRequestBuilders.post("/actors")
                .content("{\"firstName\": \"Rob\", \"lastName\": \"Jordan\", \"gender\": \"M\", \"birthDate\": \"1992-12-23\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

        verify(actorService).createActor(any(Actor.class));

    }
}