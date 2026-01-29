package com.javarush.springfinal.config.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "my-jwt.config")
public class JwtProperties {

    private String secret = "secret";
    private long validateTime = (1000 * 60 * 15);

}
