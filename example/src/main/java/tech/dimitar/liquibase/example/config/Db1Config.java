package tech.dimitar.liquibase.example.config;

import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"tech.dimitar.liquibase.example.database.db1*"},
        entityManagerFactoryRef = "db1PostgresEntityManagerFactory",
        transactionManagerRef = "db1PostgresTransactionManager"
)
public class Db1Config {

    @Primary
    @Bean(name = "db1PostgresDataSourceProperties")
    @ConfigurationProperties("db1.datasource")
    public DataSourceProperties db1PostgresDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Primary
    @Bean(name = "db1PostgresDataSource")
    @ConfigurationProperties("db1.datasource.configuration")
    public DataSource db1Datasource(@Qualifier("db1PostgresDataSourceProperties") DataSourceProperties db1PostgresDataSourceProperties) {
        return db1PostgresDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }


    @Primary
    @Bean(name = "db1PostgresEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db1PostgresEntityManagerFactory(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder, @Qualifier("db1PostgresDataSource") DataSource db1Datasource) {
        return entityManagerFactoryBuilder
                .dataSource(db1Datasource)
                .packages("tech.dimitar.liquibase.example.database.db1.entities")
                .build();
    }

    @Primary
    @Bean(name = "db1PostgresTransactionManager")
    public PlatformTransactionManager db1PostgresTransactionManager(
            @Qualifier("db1PostgresEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }




}
