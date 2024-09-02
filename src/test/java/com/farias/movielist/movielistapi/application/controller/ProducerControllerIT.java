package com.farias.movielist.movielistapi.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.farias.movielist.movielistapi.domain.service.MovieService;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@SpringBootTest
@AutoConfigureMockMvc
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProducerControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MovieService movieService;

    @BeforeEach
    public void beforeAll() {
        movieService.importMovies();
    }

    @Test
    @Transactional
    void shouldGetAwardWinningIntervals() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/producers/awardWinningInterval"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.min.length()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.min[0].producer").value("Joel Silver"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.min[0].interval").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.min[0].previousWin").value(1990))
                .andExpect(MockMvcResultMatchers.jsonPath("$.min[0].followingWin").value(1991))
                .andExpect(MockMvcResultMatchers.jsonPath("$.max.length()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.max[0].producer").value("Matthew Vaughn"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.max[0].interval").value(13))
                .andExpect(MockMvcResultMatchers.jsonPath("$.max[0].previousWin").value(2002))
                .andExpect(MockMvcResultMatchers.jsonPath("$.max[0].followingWin").value(2015));

    }
}
