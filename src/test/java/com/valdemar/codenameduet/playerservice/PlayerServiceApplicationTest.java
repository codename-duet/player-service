package com.valdemar.codenameduet.playerservice;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.valdemar.codenameduet.playerservice.adapters.controller.request.RegisterPlayerRequest;
import com.valdemar.codenameduet.playerservice.ports.PlayerRepository;
import com.valdemar.codenameduet.playerservice.ports.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.RequestDispatcher;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PlayerServiceApplication.class)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ActiveProfiles("h2")
class PlayerServiceApplicationTest {


    @Autowired
    private WebApplicationContext context;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerService playerService;

    // This object will be magically initialized by the initFields method below.
    private JacksonTester<RegisterPlayerRequest> jsonPlayer;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation)).build();

        JacksonTester.initFields(this, new ObjectMapper());

        //Example on how to configure the schema, host and port
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
//                .apply(documentationConfiguration(this.restDocumentation).uris()
//                        .withScheme("https")
//                        .withHost("example.com")
//                        .withPort(443))
//                .build();
    }

    @Test
    @DisplayName("Register a player and returns the id of the new player in the header")
    public void registerPlayer() throws Exception {
        mockMvc.perform(
                post("/player")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPlayer.write(playerRequest()).getJson())
        )
                .andExpect(status().isCreated())
                .andExpect(redirectedUrlPattern("http://*/player/1"))
                .andDo(document("register-player",
                        requestFields(
                                fieldWithPath("playerName").description("Player's Name"))));
    }

    @Test
    @DisplayName("Giving an id of an existing player, returns the details of the associated player")
    public void playerDetails() throws Exception {

        long id = createPlayer("Anna");
        mockMvc.perform(
                get("/player/{playerId}", id) )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("playerName", is("Anna")))
                .andDo(document("list-player-details",
                        pathParameters(parameterWithName("playerId").description("The Id of the player")),
                        responseFields(
                                fieldWithPath("playerName").description("Player's Name"))));
    }


    @Test
    public void errorExample() throws Exception {
        this.mockMvc
                .perform(get("/error")
                        .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 400)
                        .requestAttr(RequestDispatcher.ERROR_REQUEST_URI,
                                "/notes")
                        .requestAttr(RequestDispatcher.ERROR_MESSAGE,
                                "The tag 'http://localhost:8080/tags/123' does not exist"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("error", is("Bad Request")))
                .andExpect(jsonPath("timestamp", is(notNullValue())))
                .andExpect(jsonPath("status", is(400)))
                .andExpect(jsonPath("path", is(notNullValue())))
                .andDo(document("error-example",
                        responseFields(
                                fieldWithPath("error").description("The HTTP error that occurred, e.g. `Bad Request`"),
                                fieldWithPath("message").description("A description of the cause of the error"),
                                fieldWithPath("path").description("The path to which the request was made"),
                                fieldWithPath("status").description("The HTTP status code, e.g. `400`"),
                                fieldWithPath("timestamp").description("The time, in milliseconds, at which the error occurred"))));
    }
    private long createPlayer(String playerName) {
        return playerRepository.createPlayer(playerName);
    }

    private RegisterPlayerRequest playerRequest() {
        return RegisterPlayerRequest.builder()
                .playerName("valdemar")
                .build();
    }

}