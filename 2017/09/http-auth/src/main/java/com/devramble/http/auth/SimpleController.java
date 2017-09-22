

package com.devramble.http.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @RequestMapping(method = RequestMethod.GET, value = "/resource/basic")
    public String basicAuthResource() {
        return "Basic authentication resuorce";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/resource/digest")
    public String digestAuthResource() {
        return "Digest authentication resource";
    }
}
