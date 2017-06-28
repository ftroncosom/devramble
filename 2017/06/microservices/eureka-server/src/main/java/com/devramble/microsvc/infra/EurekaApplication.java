package com.devramble.microsvc.infra;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/*
 * 
 * Copyright (c) Vektorsoft Ltd. (http://www.vektorsoft.com)
 * 
 * Unauthorized copying of this file, or any part of it, via any medium ,is strictly prohibited.
 * Content of this file is proprietary and confidential.
 * 
 */

/**
 * Eureka server application.
 *
 * @author Vladimir Djurovic <vdjurovic@vektorsoft.com>
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

    public static void main(String... args){
        SpringApplication.run(EurekaApplication.class, args);
    }
}
