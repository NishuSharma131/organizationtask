package org.rsystems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"org.rsystems"})
@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class})
//@EntityScan("org.rsystems.model")
//@EnableJpaRepositories("org.rsystems.dao")
public class OrgServiceApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(OrgServiceApplication.class, args);
        System.out.println("Hello world!");
    }



}

//extends SpringBootServletInitializer