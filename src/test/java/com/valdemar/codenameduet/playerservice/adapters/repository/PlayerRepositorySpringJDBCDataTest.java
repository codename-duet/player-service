package com.valdemar.codenameduet.playerservice.adapters.repository;

import com.valdemar.codenameduet.playerservice.config.DatabaseProperties;
import com.valdemar.codenameduet.playerservice.config.RepositoryConfig;
import com.valdemar.codenameduet.playerservice.model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {DatabaseProperties.class, RepositoryConfig.class})
@AutoConfigureJdbc
@ActiveProfiles("h2")
class PlayerRepositorySpringJDBCDataTest {

    @Autowired
    private PlayerRepositorySpringJDBCData repository;

    @Test
    public void exerciseRepositoryForSimpleEntity() {
        Player player = repository.save(new Player("Valdemar"));

        assertThat(player.getId()).isNotNull();
        assertThat(player.getName()).isEqualTo("Valdemar");
    }

    //TODO: Complete
}