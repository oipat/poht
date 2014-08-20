package org.tapiok.blogi.config;

import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
public class PersistenceConfig {

    @Bean
    @Profile("prod")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setPersistenceUnitName("blogiPU");
        return entityManagerFactory;
    }
    
    @Bean
    @Profile("prod")
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        
        return transactionManager;
    }
	
//	@Bean
//	@Profile("dev")
//	DatabasePopulator databasePopulator(DataSource dataSource)
//			throws ScriptException, SQLException {
//		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//		populator.setContinueOnError(true);
//		populator.setIgnoreFailedDrops(true);
//		populator.addScript(new ClassPathResource("dev-test-data.sql"));
//		populator.populate(dataSource.getConnection());
//		return populator;
//	}
}
