package com.valdemar.codenameduet.playerservice.config;

import com.valdemar.codenameduet.playerservice.ports.PlayerRepository;
import com.valdemar.codenameduet.playerservice.service.PlayerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public PlayerServiceImpl playerService(final PlayerRepository playerRepository) {
        return new PlayerServiceImpl(playerRepository);
    }
}
