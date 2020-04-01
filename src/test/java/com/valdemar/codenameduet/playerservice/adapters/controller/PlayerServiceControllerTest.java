package com.valdemar.codenameduet.playerservice.adapters.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.valdemar.codenameduet.playerservice.adapters.controller.PlayerServiceController;
import com.valdemar.codenameduet.playerservice.adapters.controller.request.RegisterPlayerRequest;
import com.valdemar.codenameduet.playerservice.ports.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PlayerServiceController.class)
//@ContextConfiguration(classes = { SpringTestConfiguration.class })
class PlayerServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    // This object will be magically initialized by the initFields method below.
    private JacksonTester<RegisterPlayerRequest> jsonPlayer;


    @BeforeEach
    public void setup() {
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void registerPlayerReturnsHttpStatusOk() throws Exception {
        when(playerService.registerPlayer(eq("valdemar")))
                .thenReturn(1L);

        mockMvc.perform(
                post("/player")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPlayer.write(playerRequest()).getJson())
        )
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/player/1"));
    }

    private RegisterPlayerRequest playerRequest() {
        return RegisterPlayerRequest.builder()
                .playerName("valdemar")
                .build();
    }
}