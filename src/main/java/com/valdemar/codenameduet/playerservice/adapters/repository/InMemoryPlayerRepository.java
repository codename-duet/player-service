package com.valdemar.codenameduet.playerservice.adapters.repository;


import com.valdemar.codenameduet.playerservice.model.Player;
import com.valdemar.codenameduet.playerservice.ports.PlayerRepository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public final class InMemoryPlayerRepository implements PlayerRepository {

    private static final ConcurrentMap<Long, String> registeredPlayers = new ConcurrentHashMap<>();
    private static final AtomicLong idCounter = new AtomicLong();

    @Override
    public long createPlayer(String playerName) {
        long id = idCounter.getAndIncrement();
        registeredPlayers.put(id, playerName);
        return id;
    }

    @Override
    public Player playerDetails(long playerId) {
        //TODO: handle exception when id does not exist
        return new Player(registeredPlayers.get(playerId)).withId(playerId);
    }
}
