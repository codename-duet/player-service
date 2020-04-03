package com.valdemar.codenameduet.playerservice.adapters.repository;

import com.valdemar.codenameduet.playerservice.model.Player;
import com.valdemar.codenameduet.playerservice.ports.PlayerRepository;

public class PlayerRepoSpringJDBCDataAdapter implements PlayerRepository {

    private final PlayerRepositorySpringJDBCData playerRepository;

    public PlayerRepoSpringJDBCDataAdapter(PlayerRepositorySpringJDBCData playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public long createPlayer(String playerName) {
        Player player = playerRepository.save( new Player(playerName));
        return player.getId();
    }

    @Override
    public Player playerDetails(long playerId) {
        return playerRepository.findById(playerId).orElseThrow();
    }
}
