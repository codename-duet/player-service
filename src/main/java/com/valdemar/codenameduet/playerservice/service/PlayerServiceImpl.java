package com.valdemar.codenameduet.playerservice.service;

import com.valdemar.codenameduet.playerservice.model.Player;
import com.valdemar.codenameduet.playerservice.ports.PlayerRepository;
import com.valdemar.codenameduet.playerservice.ports.PlayerService;

public final class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public long registerPlayer(final String playerName) {
        return playerRepository.createPlayer(playerName);
    }

    @Override
    public Player playerDetails(long playerId) {
        return playerRepository.playerDetails(playerId);
    }
}
