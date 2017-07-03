

package com.devramble.microsvc.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @RequestMapping (method = RequestMethod.POST, value = "/send-email", consumes = {MediaType.TEXT_PLAIN_VALUE})
    public void sendMessage(@RequestBody String address) {
        System.out.println("Sending email to address " + address);
    }
}
