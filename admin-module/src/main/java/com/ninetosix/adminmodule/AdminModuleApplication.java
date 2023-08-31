package com.ninetosix.adminmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.ninetosix.coremodule"})
@EnableJpaRepositories(basePackages = {"com.ninetosix.coremodule"})
public class AdminModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminModuleApplication.class, args);
    }

}
