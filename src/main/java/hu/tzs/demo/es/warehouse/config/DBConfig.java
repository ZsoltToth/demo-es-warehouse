package hu.tzs.demo.es.warehouse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"hu.tzs.demo.es.warehouse.persist"})
public class DBConfig {
}
