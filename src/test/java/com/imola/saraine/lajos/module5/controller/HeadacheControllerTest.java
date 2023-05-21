package com.imola.saraine.lajos.module5.controller;

import com.imola.saraine.lajos.module5.model.Headache;
import com.imola.saraine.lajos.module5.model.HeadacheType;
import com.imola.saraine.lajos.module5.service.HeadacheService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.when;

@WebMvcTest(HeadacheController.class)
@ActiveProfiles("oauth2")
class HeadacheControllerTest {

    private Headache headache;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HeadacheService headacheService;


    @BeforeEach
    void setUp() {
        headache = Headache.builder()
                .id(1L)
                .type(HeadacheType.MIGRAINE)
                .strength(5)
                .occurance(LocalDate.now())
                .build();
        OAuth2User principal = new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                Collections.singletonMap("name", "testUser"),
                "name"
        );

        SecurityContextHolder.getContext().setAuthentication(
                new OAuth2AuthenticationToken(principal, Collections.emptyList(), "github")
        );

    }
    @Test
    void lookUpHeadache() throws Exception {
        Long id = 1L;
        headache.setId(id);

        when(headacheService.lookUpHeadache(id)).thenReturn(headache);

        mockMvc.perform(MockMvcRequestBuilders.get("/headaches/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
    }
}