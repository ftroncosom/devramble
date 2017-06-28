/*
 * 
 * Copyright (c) Vektorsoft Ltd. (http://www.vektorsoft.com)
 * 
 * Unauthorized copying of this file, or any part of it, via any medium ,is strictly prohibited.
 * Content of this file is proprietary and confidential.
 * 
 */

package com.devramble.microsvc.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Spring Boot application main class.
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Application {

    public static void main(String... args){
        SpringApplication.run(Application.class, args);
    }
}
