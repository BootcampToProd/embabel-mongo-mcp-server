package com.bootcamptoprod;

import com.embabel.agent.config.annotation.EnableAgents;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAgents
@SpringBootApplication
public class EmbabelMongoMcpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmbabelMongoMcpServerApplication.class, args);
    }

}
