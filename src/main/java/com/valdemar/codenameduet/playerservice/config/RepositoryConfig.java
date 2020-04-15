package com.valdemar.codenameduet.playerservice.config;

import com.valdemar.codenameduet.playerservice.adapters.repository.InMemoryPlayerRepository;
import com.valdemar.codenameduet.playerservice.adapters.repository.PlayerRepoSpringJDBCDataAdapter;
import com.valdemar.codenameduet.playerservice.adapters.repository.PlayerRepositorySpringJDBCData;
import com.valdemar.codenameduet.playerservice.ports.PlayerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
@EnableJdbcRepositories(basePackages = "com.valdemar.codenameduet.playerservice.adapters.repository")
public class RepositoryConfig extends AbstractJdbcConfiguration {

    @Bean
    public DataSource dataSource(final DatabaseProperties databaseProperties) {
        return databaseProperties.dataSource();
    }

    @Bean
    public NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public PlayerRepository inMemoryPlayerRepository() {
        return new InMemoryPlayerRepository();
    }

    @Bean
    public PlayerRepository playerRepository(PlayerRepositorySpringJDBCData playerRepository) {
        return new PlayerRepoSpringJDBCDataAdapter(playerRepository);
    }
}
