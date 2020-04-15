package com.valdemar.codenameduet.playerservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.validation.constraints.NotBlank;

@Component
@ConfigurationProperties(prefix = "custom.db")
@Getter
@Setter
public class DatabaseProperties {
    @NotBlank
    private String url;
    @NotBlank
    private String driverClassName;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }

}
