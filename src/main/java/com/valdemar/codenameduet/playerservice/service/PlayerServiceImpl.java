package com.valdemar.codenameduet.playerservice.service;

import com.valdemar.codenameduet.playerservice.model.Player;
import com.valdemar.codenameduet.playerservice.ports.PlayerRepository;
import com.valdemar.codenameduet.playerservice.ports.PlayerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public long registerPlayer(final String playerName) {
        log.info("Registering a new player: {}", playerName);
        return playerRepository.createPlayer(playerName);
    }

    @Override
    public Player playerDetails(long playerId) {
        log.info("Getting player details for id: {}", playerId);

        return playerRepository.playerDetails(playerId);
    }
}
