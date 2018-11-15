package com.cafebabe.gethouse.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.cafebabe.gethouse.repository")
@EnableTransactionManagement
public class JPAConfig {
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        HibernateJpaVendorAdapter jpaVendor=new HibernateJpaVendorAdapter();
        jpaVendor.setGenerateDdl(false);

        LocalContainerEntityManagerFactoryBean entiryManagerFactory=new LocalContainerEntityManagerFactoryBean();
        entiryManagerFactory.setDataSource(dataSource());
        entiryManagerFactory.setJpaVendorAdapter(jpaVendor);
        entiryManagerFactory.setPackagesToScan("com.cafebabe.gethouse.entity");
        return entiryManagerFactory;

    }
}

