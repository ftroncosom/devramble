
package com.devramble.microsvc.user;

import com.btmatthews.springboot.memcached.EnableMemcached;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Spring Boot application main class.
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@EnableMemcached
public class Application {

    public static void main(String... args){
        SpringApplication.run(Application.class, args);
    }
}
