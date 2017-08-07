

package com.devramble.microsvc.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);
    
//    @Autowired
//    private GaugeService gauge;

    @RequestMapping (method = RequestMethod.POST, value = "/send-email", consumes = {MediaType.TEXT_PLAIN_VALUE})
    public void sendMessage(@RequestBody String address) {
        
        LOGGER.info("Sending memail to address {}", address);
        // moved to interceptor
        //gauge.submit("msg.gauge.value", Math.random() * 100);
    }
}
