package com.valdemar.codenameduet.playerservice.adapters.controller;

import com.valdemar.codenameduet.playerservice.adapters.controller.request.RegisterPlayerRequest;
import com.valdemar.codenameduet.playerservice.adapters.controller.response.PlayerDetailResponse;
import com.valdemar.codenameduet.playerservice.model.Player;
import com.valdemar.codenameduet.playerservice.ports.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/player")
@Slf4j
public class PlayerServiceController {

    private PlayerService playerService;

    @Autowired
    public PlayerServiceController(final PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping(value = "")
    ResponseEntity<?> registerPlayer(UriComponentsBuilder uriComponentsBuilder, @RequestBody RegisterPlayerRequest request) {

        log.info("Request for register a new player: {}", request.getPlayerName());

        long id = playerService.registerPlayer(request.getPlayerName());

        UriComponents uriComponents =
                uriComponentsBuilder.path("/player/{id}").buildAndExpand(id);

        log.info("Player {} registered", request.getPlayerName());

        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping("/{playerId}")
    PlayerDetailResponse playerDetails(@PathVariable long playerId) {
        log.info("Request for getting details of the existing player Id: {}", playerId);

        Player player = playerService.playerDetails(playerId);

        return PlayerDetailResponse.builder().playerName(player.getName()).build();
    }


}
