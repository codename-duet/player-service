package com.valdemar.codenameduet.playerservice.ports;

import com.valdemar.codenameduet.playerservice.model.Player;

/**
 * Primary Port representing the business core.
 */
public interface PlayerService {

    long registerPlayer(String playerName);

    Player playerDetails(long playerId);
}
