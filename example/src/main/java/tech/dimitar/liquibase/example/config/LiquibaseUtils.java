package tech.dimitar.liquibase.example.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;

import javax.sql.DataSource;

public final class LiquibaseUtils {

    private LiquibaseUtils() {
    }

    public static SpringLiquibase springLiquibase(DataSource dataSource, LiquibaseProperties properties) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(properties.getChangeLog());
        liquibase.setContexts(properties.getContexts());
        liquibase.setDefaultSchema(properties.getDefaultSchema());
        liquibase.setDropFirst(properties.isDropFirst());
        liquibase.setShouldRun(properties.isEnabled());
        liquibase.setLabels(properties.getLabels());
        liquibase.setChangeLogParameters(properties.getParameters());
        liquibase.setRollbackFile(properties.getRollbackFile());
        return liquibase;
    }

}
