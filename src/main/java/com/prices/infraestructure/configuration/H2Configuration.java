package com.prices.infraestructure.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.prices.infraestructure.repositories")
public class H2Configuration {

}
