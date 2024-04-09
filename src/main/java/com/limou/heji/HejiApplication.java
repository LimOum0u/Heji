package com.limou.heji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"com.limou.heji.model.po"})
@EnableJpaRepositories("com.limou.heji.repository")
@EnableJpaAuditing
public class HejiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HejiApplication.class, args);
    }

}
