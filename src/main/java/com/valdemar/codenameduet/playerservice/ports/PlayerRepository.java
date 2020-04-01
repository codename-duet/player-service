package com.valdemar.codenameduet.playerservice.ports;


import com.valdemar.codenameduet.playerservice.model.Player;

/**
 * Secondary or "driven" Port for obtaining information about players
 */
public interface PlayerRepository {

    long createPlayer(String playerName);

    Player playerDetails(long playerId);
}
