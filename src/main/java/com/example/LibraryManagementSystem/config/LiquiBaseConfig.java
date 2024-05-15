package com.example.sales.system.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LiquiBaseConfig {
    @Value("${spring.datasource.username}")
    private String UserName;
    @Value("${spring.datasource.password}")
    private String Password;
    @Value("${spring.datasource.url}")
    private String Url;
    @Value("${spring.datasource.driver-class-name}")
    private String Driver;
    @Bean
    public SpringLiquibase springLiquibase()
    {
        SpringLiquibase sp = new SpringLiquibase();
        sp.setChangeLog("classpath:db/changelog-master.yml");
        sp.setDataSource(DataSourceBuilder.create()
                .username(UserName)
                .password(Password)
                .url(Url)
                .driverClassName(Driver)
                .build());
        return sp;
    }
}
