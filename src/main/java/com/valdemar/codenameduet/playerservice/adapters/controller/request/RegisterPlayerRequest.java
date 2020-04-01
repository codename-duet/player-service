package com.valdemar.codenameduet.playerservice.adapters.controller.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE) //Hides the constructor to force usage of the Builder.
@JsonDeserialize(builder = RegisterPlayerRequest.RegisterPlayerRequestBuilder.class)
public final class RegisterPlayerRequest {

    private final String playerName;

    @JsonPOJOBuilder(withPrefix = "")
    public static class RegisterPlayerRequestBuilder {
        // Lombok will add constructor, setters, build method
    }
}
