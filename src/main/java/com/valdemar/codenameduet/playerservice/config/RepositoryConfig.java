package com.valdemar.codenameduet.playerservice.config;

import com.valdemar.codenameduet.playerservice.adapters.repository.InMemoryPlayerRepository;
import com.valdemar.codenameduet.playerservice.ports.PlayerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    @Bean
    public PlayerRepository inMemoryPlayerRepository() {
        return new InMemoryPlayerRepository();
    }
}
