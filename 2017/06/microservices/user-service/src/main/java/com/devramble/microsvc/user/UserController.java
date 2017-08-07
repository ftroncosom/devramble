package com.devramble.microsvc.user;

import com.codahale.metrics.annotation.Timed;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RibbonClient(name = "messaging-service")
public class UserController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Value("${user.greeting}")
    private String greeting;

    @Autowired
    private HystrixMessageService msgService;
    
//    @Autowired
//    private CounterService counterService;

    @Timed
    @RequestMapping(method = RequestMethod.GET, value = "/greet")
    public @ResponseBody String greet() {
        return greeting;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void createUser(@RequestBody User user, HttpServletRequest request) {
        LOGGER.info("Creating user: name={}, email={}", user.getName(), user.getEmail());
        // moved to interceptor
        //counterService.increment("counter.user.create");
        // invoke messgaing service
        msgService.sendMessage(user);
    }
}
