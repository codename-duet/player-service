package com.valdemar.codenameduet.playerservice.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__(@PersistenceConstructor))
public final class Player {
    @Id @With
    private final Long id;
    private final String name;

    public Player(String name) {

        this.id = null;
        this.name = name;
    }
}
