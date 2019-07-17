package com.tiennv.all.config;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import org.springframework.core.env.Environment;


@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:database.properties"})
//@PropertySource(value = {"classpath:application.properties", "classpath:application-mysql.properties"})
public class HibernateConfig {
	@Autowired
    private Environment env;
	
//	
//    @Value("${db.driver}")
//    private String DB_DRIVER;
//
//    @Value("${db.password}")
//    private String DB_PASSWORD;
//
//    @Value("${db.url}")
//    private String DB_URL;
//
//    @Value("${db.username}")
//    private String DB_USERNAME;
//
//    @Value("${hibernate.dialect}")
//    private String HIBERNATE_DIALECT;
//
//    @Value("${hibernate.show_sql}")
//    private String HIBERNATE_SHOW_SQL;
//
//    @Value("${hibernate.hbm2ddl.auto}")
//    private String HIBERNATE_HBM2DDL_AUTO;
//
//    @Value("${entitymanager.packagesToScan}")
//    private String ENTITYMANAGER_PACKAGES_TO_SCAN;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
    	//Cách 1: dùng enviroment
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(env.getProperty("entitymanager.packagesToScan"));
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.put("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
        hibernateProperties.put("hibernate.hbm2ddl.auto",env.getProperty("hibernate.hbm2ddl.auto"));
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
      //Cách 1: dùng enviroment
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        
        //Cách 2: dùng @value
//        dataSource.setDriverClassName(DB_DRIVER);
//        dataSource.setUrl(DB_URL);
//        dataSource.setUsername(DB_USERNAME);
//        dataSource.setPassword(DB_PASSWORD);
        return dataSource;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject());
        return txManager;
    }
    
    @Bean
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }
}

