package tech.dimitar.liquibase.example.config;

import liquibase.integration.spring.SpringLiquibase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(prefix = "spring.liquibase", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(LiquibaseProperties.class)
@AllArgsConstructor
public class LiquibaseDb1Config {

    @Bean
    @ConfigurationProperties(prefix = "db1.datasource.liquibase")
    public LiquibaseProperties primaryLiquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Primary
    @Bean
    public SpringLiquibase primaryLiquibase(@Qualifier("db1PostgresDataSource") DataSource db1Datasource) {
        return LiquibaseUtils.springLiquibase(db1Datasource, primaryLiquibaseProperties());
    }


}