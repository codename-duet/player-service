package com.valdemar.codenameduet.playerservice.adapters.controller.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE) //Hides the constructor to force usage of the Builder.
public final class PlayerDetailResponse {
    private final String playerName;
}
