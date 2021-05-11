package com.wap.musichub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // auditing이 가능하게
@SpringBootApplication
public class MusichubApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusichubApplication.class, args);
    }

}
