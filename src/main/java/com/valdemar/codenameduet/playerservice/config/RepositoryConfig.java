package com.valdemar.codenameduet.playerservice.config;

import com.valdemar.codenameduet.playerservice.adapters.repository.InMemoryPlayerRepository;
import com.valdemar.codenameduet.playerservice.adapters.repository.PlayerRepoSpringJDBCDataAdapter;
import com.valdemar.codenameduet.playerservice.adapters.repository.PlayerRepositorySpringJDBCData;
import com.valdemar.codenameduet.playerservice.ports.PlayerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Configuration
@EnableJdbcRepositories(basePackages="com.valdemar.codenameduet.playerservice.adapters.repository")
public class RepositoryConfig extends AbstractJdbcConfiguration {

    @Bean
    public PlayerRepository inMemoryPlayerRepository() {
        return new InMemoryPlayerRepository();
    }

    @Bean
    public PlayerRepository playerRepository( PlayerRepositorySpringJDBCData playerRepository) {
        return new PlayerRepoSpringJDBCDataAdapter( playerRepository );
    }
}
