package com.valdemar.codenameduet.playerservice.adapters.repository;

import com.valdemar.codenameduet.playerservice.model.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepositorySpringJDBCData extends CrudRepository<Player, Long> {

}
