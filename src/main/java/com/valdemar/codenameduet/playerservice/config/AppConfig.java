package com.valdemar.codenameduet.playerservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DatabaseProperties.class, RepositoryConfig.class, ServiceConfig.class})
public class AppConfig {
}
