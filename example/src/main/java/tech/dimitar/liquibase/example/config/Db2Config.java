package tech.dimitar.liquibase.example.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        basePackages = {"tech.dimitar.liquibase.example.database.db2*"},
        entityManagerFactoryRef = "db2PostgresEntityManagerFactory",
        transactionManagerRef = "db2PostgresTransactionManager"
)
public class Db2Config {

    @Bean(name = "db2PostgresDataSourceProperties")
    @ConfigurationProperties("db2.datasource")
    public DataSourceProperties db2PostgresDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean(name = "db2PostgresDataSource")
    @ConfigurationProperties("db2.datasource.configuration")
    public DataSource db2Datasource(@Qualifier("db2PostgresDataSourceProperties") DataSourceProperties db2PostgresDataSourceProperties) {
        return db2PostgresDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "db2PostgresEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db2PostgresEntityManagerFactory(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder, @Qualifier("db2PostgresDataSource") DataSource db2Datasource) {
        return entityManagerFactoryBuilder
                .dataSource(db2Datasource)
                .packages("tech.dimitar.liquibase.example.database.db2.entities")
                .build();
    }

    @Bean(name = "db2PostgresTransactionManager")
    public PlatformTransactionManager db2PostgresTransactionManager(
            @Qualifier("db2PostgresEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }


}
