/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.devramble.microsvc.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @Value("${user.greeting}")
    private String greeting;

    @RequestMapping (method = RequestMethod.GET, value = "/greet")
    public @ResponseBody String greet() {
        return greeting;
    }
}
