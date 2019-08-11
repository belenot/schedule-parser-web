package com.belenot.mirea.schedule.config.support;

import java.util.Properties;

import javax.sql.DataSource;

import com.belenot.mirea.schedule.config.SessionFactoryConfig;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

@Configuration
//@Profile( "test" )
public class DefaultSessionFactoryConfig implements SessionFactoryConfig {
    
    @Autowired
    Environment env;
    
    @Bean
    public SessionFactory sessionFactory() {
	LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
	builder.scanPackages("com.belenot.mirea.schedule.domain").addProperties(hibernateProperties());
	return builder.buildSessionFactory();
    }
    @Bean
    public DataSource dataSource() {
	DriverManagerDataSource dataSource = new DriverManagerDataSource();
	dataSource.setDriverClassName("org.h2.Driver");
	dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1;TRACE_LEVEL_SYSTEM_OUT=2");
	dataSource.setUsername("sa");
	dataSource.setPassword("sa");
	return dataSource;
    }
    private final Properties hibernateProperties() {
	Properties hibernateProperties = new Properties();
	hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
	hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	hibernateProperties.setProperty("hibernate.show_sql", "false");
	return hibernateProperties;
    }
}
