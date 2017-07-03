
package com.devramble.microsvc.user;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HystrixMessageService {
    
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand (fallbackMethod = "fallback")
    public void sendMessage(User user) {
        restTemplate.postForObject("http://messaging-service/send-email", user.getEmail(), String.class);
    }
    
    public void fallback(User user) {
        System.out.println("Hystrix fallback method");
    }
}
