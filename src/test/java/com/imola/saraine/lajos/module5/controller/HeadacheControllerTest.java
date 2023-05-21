package com.imola.saraine.lajos.module5.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imola.saraine.lajos.module5.model.Headache;
import com.imola.saraine.lajos.module5.model.HeadacheType;
import com.imola.saraine.lajos.module5.service.HeadacheService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HeadacheController.class)
class HeadacheControllerTest {

    private Headache headache;

    private static final long ID_1 = 1L;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private HeadacheService headacheService;


    @BeforeEach
    void setUp() {
        headache = Headache.builder()
                .id(ID_1)
                .type(HeadacheType.MIGRAINE)
                .strength(5)
                .occurance(LocalDate.now())
                .build();

    }
    @Test
    @WithMockUser
    void lookUpHeadache() throws Exception {
        when(headacheService.lookUpHeadache(ID_1)).thenReturn(headache);

        mockMvc.perform(MockMvcRequestBuilders.get("/headaches/" + ID_1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID_1));
    }


    @Test
    @WithMockUser
    public void markDownHeadache() throws Exception {
        when(headacheService.markDownHeadache(any(Headache.class))).thenReturn(headache);

        mockMvc.perform(post("/headaches")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(headache))
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.strength").value(headache.getStrength()));
    }

    @Test
    @WithMockUser
    void testChangeHeadacheStrength() throws Exception {

        Headache updatedHeadache = new Headache(ID_1, HeadacheType.MIGRAINE, 7, LocalDate.now());
        when(headacheService.changeHeadacheStrength(ID_1, 7)).thenReturn(updatedHeadache);

        mockMvc.perform(MockMvcRequestBuilders.put("/headaches/{id}/strength/{strength}", ID_1, 7)
                    .with(csrf()))


                .andExpect(status().isOk())
                .andExpect(jsonPath("$.strength").value(updatedHeadache.getStrength()));
    }
}